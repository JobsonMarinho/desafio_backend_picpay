package net.hypedmc.picpay.config

import net.hypedmc.picpay.entity.WalletType
import net.hypedmc.picpay.repository.WalletTypeRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration

@Configuration
class DataLoader(
    private val walletTypeRepository: WalletTypeRepository
) : CommandLineRunner {

    override fun run(vararg args: String) {
        WalletType.Enum.entries.forEach { type ->
            if (!walletTypeRepository.existsById(type.id)) {
                walletTypeRepository.save(type.getInstance())
            }
        }
    }

}