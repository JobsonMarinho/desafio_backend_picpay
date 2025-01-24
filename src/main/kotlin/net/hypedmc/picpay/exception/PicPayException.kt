package net.hypedmc.picpay.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import java.lang.RuntimeException

open class PicPayException : RuntimeException() {

    open fun toProblemDetail() : ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        problemDetail.title = "PicPay internal server error"
        return problemDetail
    }

}