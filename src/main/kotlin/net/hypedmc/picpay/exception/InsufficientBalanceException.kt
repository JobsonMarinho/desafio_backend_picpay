package net.hypedmc.picpay.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class InsufficientBalanceException: PicPayException() {

    override fun toProblemDetail(): ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        problemDetail.title = "Insufficient balance"
        return problemDetail
    }

}