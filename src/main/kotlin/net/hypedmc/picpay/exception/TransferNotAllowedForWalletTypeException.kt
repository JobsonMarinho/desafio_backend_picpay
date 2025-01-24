package net.hypedmc.picpay.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class TransferNotAllowedForWalletTypeException: PicPayException() {

    override fun toProblemDetail(): ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        problemDetail.title = "Transfer not allowed for wallet type"
        return problemDetail
    }

}