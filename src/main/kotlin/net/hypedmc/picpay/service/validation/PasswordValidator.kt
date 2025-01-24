package net.hypedmc.picpay.service.validation

import net.hypedmc.picpay.controller.dto.CreateWalletDto
import net.hypedmc.picpay.exception.InvalidPasswordException
import org.springframework.stereotype.Component

@Component
class PasswordValidator : Validator<CreateWalletDto> {
    private val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$".toRegex()

    override fun validate(data: CreateWalletDto) {
        if (!isValidPassword(data.password)) {
            throw InvalidPasswordException("The provided password is invalid")
        }
    }

    fun isValidPassword(password: String): Boolean {
        return passwordRegex.matches(password)
    }
}