package net.hypedmc.picpay.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "picpay_transfer")
data class Transfer(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty("id")
    val id: String? = null,

    @ManyToOne
    @JoinColumn(name = "wallet_sender_id")
    @JsonProperty("sender")
    val sender: Wallet,

    @ManyToOne
    @JoinColumn(name = "wallet_receiver_id")
    @JsonProperty("receiver")
    val receiver: Wallet,

    @Column(name = "amount")
    @JsonProperty("amount")
    val amount: BigDecimal

)