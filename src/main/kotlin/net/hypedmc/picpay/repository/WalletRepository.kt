package net.hypedmc.picpay.repository

import net.hypedmc.picpay.entity.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface WalletRepository : JpaRepository<Wallet, Long> {
    fun findByIdentityOrEmail(identity: String, email: String): Optional<Wallet>
    fun existsByEmail(email: String): Boolean
    fun existsByIdentity(identity: String): Boolean
}