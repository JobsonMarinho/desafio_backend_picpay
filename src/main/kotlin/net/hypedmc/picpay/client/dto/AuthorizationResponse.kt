package net.hypedmc.picpay.client.dto

data class AuthorizationData(val authorization: Boolean)

data class AuthorizationResponse(
    val status: String,
    val data: AuthorizationData
)
