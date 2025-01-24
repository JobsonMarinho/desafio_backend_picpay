package net.hypedmc.picpay.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import net.hypedmc.picpay.entity.Transfer
import java.math.BigDecimal

class ResponseTransferDto {

    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("sender")
    var sender: Long? = null

    @JsonProperty("receiver")
    var receiver: Long? = null

    @JsonProperty("amount")
    var amount: BigDecimal? = null

    fun create(transfer: Transfer): ResponseTransferDto {
        id = transfer.id!!
        sender = transfer.sender.id!!
        receiver = transfer.receiver.id!!
        amount = transfer.amount
        return this
    }
}