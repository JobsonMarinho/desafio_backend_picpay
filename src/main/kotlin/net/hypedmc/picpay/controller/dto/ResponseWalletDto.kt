package net.hypedmc.picpay.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty
import net.hypedmc.picpay.entity.Wallet
import net.hypedmc.picpay.entity.WalletType
import java.math.BigDecimal

class ResponseWalletDto {

    @JsonProperty("id")
    private var id: Long? = null

    @JsonProperty("name")
    private var name: String? = null

    @JsonProperty("identity")
    private var identity: String? = null

    @JsonProperty("email")
    private var email: String? = null

    @JsonProperty("wallet_type")
    private var walletType: WalletType.Enum? = null

    @JsonProperty("balance")
    private var balance: BigDecimal = BigDecimal.ZERO

    fun create(wallet: Wallet): ResponseWalletDto {
        id = wallet.id!!
        name = wallet.name
        identity = wallet.identity
        email = wallet.email
        walletType = WalletType.Enum.valueOf(wallet.walletType.description.uppercase())
        balance = wallet.balance
        return this
    }
}