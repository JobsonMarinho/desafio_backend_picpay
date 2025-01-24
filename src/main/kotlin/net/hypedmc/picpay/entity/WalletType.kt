package net.hypedmc.picpay.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "picpay_wallet_type")
data class WalletType(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    val id: Long? = null,

    @Column(name = "description")
    @JsonProperty("description")
    val description: String,
) {

    enum class Enum(
        val id: Long, private val description: String
    ) {
        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        fun getInstance(): WalletType = WalletType(id, description)
    }

}