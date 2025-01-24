package net.hypedmc.picpay.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import net.hypedmc.picpay.entity.Wallet
import net.hypedmc.picpay.entity.WalletType

data class CreateWalletDto(

    @JsonProperty("name")
    private val name: String,

    @JsonProperty("identity")
    val identity: String,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("password")
    var password: String,

    @JsonProperty("wallet_type")
    private val walletType: WalletType.Enum
) {

    fun toWallet(): Wallet {
        return Wallet(
            name = name,
            identity = identity,
            email = email,
            password = password,
            walletType = walletType.getInstance()
        )
    }

}