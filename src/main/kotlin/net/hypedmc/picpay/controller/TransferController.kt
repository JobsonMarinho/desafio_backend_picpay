package net.hypedmc.picpay.controller

import jakarta.validation.Valid
import net.hypedmc.picpay.controller.dto.ResponseTransferDto
import net.hypedmc.picpay.controller.dto.TransferDto
import net.hypedmc.picpay.entity.Transfer
import net.hypedmc.picpay.service.TransferService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("transfer")
class TransferController(
    private val transferService: TransferService
) {

    @PostMapping
    fun transfer(
        @RequestBody @Valid transfer: TransferDto
    ): ResponseEntity<ResponseTransferDto> {
        return ResponseEntity.ok(ResponseTransferDto().create(transferService.transfer(transfer)))
    }

}