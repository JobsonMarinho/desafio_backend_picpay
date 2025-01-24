package net.hypedmc.picpay.service

import net.hypedmc.picpay.controller.dto.CreateWalletDto
import net.hypedmc.picpay.controller.dto.UpdateWalletDto
import net.hypedmc.picpay.entity.Wallet
import net.hypedmc.picpay.entity.WalletType
import net.hypedmc.picpay.exception.WalletNotFoundException
import net.hypedmc.picpay.repository.WalletRepository
import net.hypedmc.picpay.service.validation.ValidationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class WalletService(
    private val walletRepository: WalletRepository,
    private val validationService: ValidationService
) {

    fun createWallet(createWalletDto: CreateWalletDto): Wallet {
        validationService.validate(createWalletDto)
        return walletRepository.save(createWalletDto.toWallet())
    }

    fun getAllWallets(
        pageNumber: Int,
        pageSize: Int,
        sort: Sort
    ): Page<Wallet> =
        walletRepository.findAll(PageRequest.of(pageNumber, pageSize, sort))

    fun deleteWallet(id: Long) {
        walletRepository.deleteById(id)
    }

    fun updateWallet(id: Long, updateWalletDto: UpdateWalletDto): Wallet {
        val wallet = walletRepository.findById(id).orElseThrow { WalletNotFoundException(id) }
        if (updateWalletDto.name.isPresent)
            wallet.name = updateWalletDto.name.get()

        if (updateWalletDto.email.isPresent)
        {
            if (walletRepository.existsByEmail(updateWalletDto.email.get())) {
                throw IllegalArgumentException("Email already exists")
            }
            wallet.email = updateWalletDto.email.get()
        }

        if (updateWalletDto.identity.isPresent)
        {
            if (walletRepository.existsByIdentity(updateWalletDto.identity.get())) {
                throw IllegalArgumentException("Identity already exists")
            }
            wallet.identity = updateWalletDto.identity.get()
        }

        if (updateWalletDto.password.isPresent)
            wallet.password = updateWalletDto.password.get()

        if (updateWalletDto.walletType.isPresent)
            wallet.walletType = WalletType.Enum.valueOf(updateWalletDto.walletType.get().uppercase()).getInstance()

        if (updateWalletDto.balance.isPresent)
            wallet.balance = updateWalletDto.balance.get()

        return walletRepository.save(wallet)
    }

}