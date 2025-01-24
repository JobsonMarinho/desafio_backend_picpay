package net.hypedmc.picpay.service.validation

import net.hypedmc.picpay.controller.dto.CreateWalletDto
import org.springframework.stereotype.Component

@Component
class ValidationService(
    private val validators: List<Validator<CreateWalletDto>>
) {
    fun validate(data: CreateWalletDto) {
        validators.forEach { it.validate(data) }
    }
}