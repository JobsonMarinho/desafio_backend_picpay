package net.hypedmc.picpay.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class WalletNotFoundException(
    private val walletId: Long
) : PicPayException() {

    override fun toProblemDetail(): ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND)
        problemDetail.title = "Wallet not found"
        problemDetail.detail = "Wallet with id $walletId not found"
        return problemDetail
    }

}