package net.hypedmc.picpay.repository

import net.hypedmc.picpay.entity.WalletType
import org.springframework.data.jpa.repository.JpaRepository

interface WalletTypeRepository : JpaRepository<WalletType, Long> {
}