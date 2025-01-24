package net.hypedmc.picpay.service

import jakarta.transaction.Transactional
import net.hypedmc.picpay.controller.dto.TransferDto
import net.hypedmc.picpay.entity.Transfer
import net.hypedmc.picpay.entity.Wallet
import net.hypedmc.picpay.exception.InsufficientBalanceException
import net.hypedmc.picpay.exception.TransferNotAllowedForWalletTypeException
import net.hypedmc.picpay.exception.UnauthorizedTransferException
import net.hypedmc.picpay.exception.WalletNotFoundException
import net.hypedmc.picpay.repository.TransferRepository
import net.hypedmc.picpay.repository.WalletRepository
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class TransferService(
    private val transferRepository: TransferRepository,
    private val walletRepository: WalletRepository,
    private val authorizationService: AuthorizationService,
    private val notificationService: NotificationService
) {

    @Transactional
    fun transfer(transferDto: TransferDto): Transfer {
        val payer = walletRepository.findById(transferDto.payer)
            .orElseThrow { WalletNotFoundException(transferDto.payer) }
        val payee = walletRepository.findById(transferDto.payee)
            .orElseThrow { WalletNotFoundException(transferDto.payee) }

        validateTransfer(transferDto, payer)

        payer.withdraw(transferDto.amount)
        payee.deposit(transferDto.amount)

        val transfer = Transfer(
            sender = payer,
            receiver = payee,
            amount = transferDto.amount
        )

        walletRepository.save(payer)
        walletRepository.save(payee)
        transferRepository.save(transfer)

        CompletableFuture.runAsync { notificationService.sendNotification(transfer) }

        return transfer
    }

    private fun validateTransfer(transfer: TransferDto, payer: Wallet) {
        if (!payer.isTransferAllowedForWalletType()) {
            throw TransferNotAllowedForWalletTypeException()
        }

        if (!payer.isBalancerEqualOrGreaterThan(transfer.amount)) {
            throw InsufficientBalanceException()
        }

        if (!authorizationService.isAuthorized(transfer)) {
            throw UnauthorizedTransferException()
        }
    }
}