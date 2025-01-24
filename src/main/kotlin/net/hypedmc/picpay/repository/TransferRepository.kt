package net.hypedmc.picpay.repository

import net.hypedmc.picpay.entity.Transfer
import org.springframework.data.jpa.repository.JpaRepository

interface TransferRepository : JpaRepository<Transfer, String> {
}