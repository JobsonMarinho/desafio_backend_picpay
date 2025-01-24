package net.hypedmc.picpay.controller

import jakarta.validation.Valid
import net.hypedmc.picpay.controller.dto.CreateWalletDto
import net.hypedmc.picpay.controller.dto.ResponseWalletDto
import net.hypedmc.picpay.controller.dto.ResponseWalletsDto
import net.hypedmc.picpay.controller.dto.UpdateWalletDto
import net.hypedmc.picpay.service.WalletService
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("wallets")
class WalletController(
    private val walletService: WalletService
) {

    @PostMapping
    fun createWallet(@RequestBody createWalletDto: CreateWalletDto): ResponseEntity<ResponseWalletDto> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponseWalletDto().create(walletService.createWallet(createWalletDto)))
    }

    @GetMapping
    fun getAllWallets(
        @RequestParam pageNumber: Int = 0,
        @RequestParam pageSize: Int = 15,
        @RequestParam sort: Sort.Direction = Sort.Direction.ASC
    ): ResponseEntity<ResponseWalletsDto> {
        val walletsPage = walletService.getAllWallets(pageNumber, pageSize, Sort.by(sort, "id"))
        return ResponseEntity.ok(
            ResponseWalletsDto(
                content = walletsPage.content.map { wallet -> ResponseWalletDto().create(wallet) },
                totalPages = walletsPage.totalPages,
                totalElements = walletsPage.totalElements,
                currentPage = walletsPage.number,
                perPage = walletsPage.size,
                sort = walletsPage.sort.toString().split(":")[1].trim()
            )
        )
    }

    @DeleteMapping("{id}")
    fun deleteWallet(@RequestParam id: Long): ResponseEntity<Unit> {
        walletService.deleteWallet(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("{id}")
    fun updateWallet(
        @RequestParam id: Long,
        @RequestBody @Valid updateWalletDto: UpdateWalletDto
    ): ResponseEntity<ResponseWalletDto> {
        return ResponseEntity.accepted().body(ResponseWalletDto().create(walletService.updateWallet(id, updateWalletDto)))
    }

}