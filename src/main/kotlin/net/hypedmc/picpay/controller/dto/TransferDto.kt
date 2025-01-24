package net.hypedmc.picpay.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class TransferDto(

    @NotNull
    @JsonProperty("payer")
    val payer: Long,

    @NotNull
    @JsonProperty("payee")
    val payee: Long,

    @DecimalMin("0.01")
    @NotNull
    @JsonProperty("amount")
    val amount: BigDecimal
)