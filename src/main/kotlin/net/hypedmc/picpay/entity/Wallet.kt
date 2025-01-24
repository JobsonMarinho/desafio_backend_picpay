package net.hypedmc.picpay.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.math.BigDecimal

@Entity
@Table(name = "picpay_wallet")
data class Wallet(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") val id: Long? = null,

    @Column(name = "name")
    @JsonProperty("name") var name: String,

    @Column(name = "identity", unique = true)
    @JsonProperty("identity") var identity: String,

    @Column(name = "email", unique = true)
    @JsonProperty("email") var email: String,

    @Column(name = "password")
    @JsonProperty("password") var password: String,

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    @JsonProperty("walletType") var walletType: WalletType,

    @JsonProperty("balance") var balance: BigDecimal = BigDecimal.ZERO

) {

    @PrePersist
    @PreUpdate
    private fun hashPassword() {
        password = BCryptPasswordEncoder().encode(password)
    }

    fun isTransferAllowedForWalletType(): Boolean =
         this.walletType == WalletType.Enum.USER.getInstance()

    fun isBalancerEqualOrGreaterThan(amount: BigDecimal): Boolean = balance >= amount

    fun deposit(amount: BigDecimal) {
        balance += amount
    }

    fun withdraw(amount: BigDecimal) {
        balance -= amount
    }

}