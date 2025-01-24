package net.hypedmc.picpay.service.validation

import net.hypedmc.picpay.controller.dto.CreateWalletDto
import net.hypedmc.picpay.exception.WalletAlreadyExistsException
import net.hypedmc.picpay.repository.WalletRepository
import org.springframework.stereotype.Component

@Component
class CreateWalletValidator(
    private val walletRepository: WalletRepository
) : Validator<CreateWalletDto> {

    override fun validate(data: CreateWalletDto) {
        if (!isValidWallet(data)) {
            throw WalletAlreadyExistsException("Identity or email has already been registered")
        }
    }

    private fun isValidWallet(data: CreateWalletDto): Boolean {
        return !walletRepository.findByIdentityOrEmail(data.identity, data.email).isPresent
    }
}