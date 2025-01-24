package net.hypedmc.picpay.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.math.BigDecimal
import java.util.Optional

data class UpdateWalletDto(
    @field:NotBlank(message = "Name cannot be blank")
    val name: Optional<String>,

    @field:Pattern(regexp = "^[0-9]{11}\$", message = "Identity must be a valid CPF with 11 digits")
    val identity: Optional<String>,

    @field:Email(message = "Invalid email format")
    val email: Optional<String>,

    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$",
        message = "Password must have at least 8 characters, one uppercase, one lowercase, one digit, and one special character"
    )
    val password: Optional<String>,

    @field:NotBlank(message = "Wallet type cannot be blank")
    val walletType: Optional<String>,

    @field:Pattern(regexp = "\\d+", message = "Balance must be a valid decimal number")
    val balance: Optional<BigDecimal>
)