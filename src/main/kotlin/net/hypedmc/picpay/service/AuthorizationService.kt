package net.hypedmc.picpay.service

import net.hypedmc.picpay.client.AuthorizationClient
import net.hypedmc.picpay.controller.dto.TransferDto
import net.hypedmc.picpay.entity.Transfer
import net.hypedmc.picpay.exception.UnauthorizedTransferException
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val authorizationClient: AuthorizationClient
) {

    fun isAuthorized(transfer: TransferDto): Boolean {
        val response = authorizationClient.isAuthorized()

        if (response.statusCode.isError) {
            throw UnauthorizedTransferException()
        }

        return response.body?.data?.authorization ?: false
    }

}