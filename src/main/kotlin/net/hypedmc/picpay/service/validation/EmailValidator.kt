package net.hypedmc.picpay.service.validation

import net.hypedmc.picpay.controller.dto.CreateWalletDto
import net.hypedmc.picpay.exception.InvalidEmailException
import org.springframework.stereotype.Component

@Component
class EmailValidator : Validator<CreateWalletDto> {
    private val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()

    override fun validate(data: CreateWalletDto) {
        if (!isValidEmail(data.email)) {
            throw InvalidEmailException("The provided email is invalid: ${data.email}")
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return emailRegex.matches(email)
    }
}