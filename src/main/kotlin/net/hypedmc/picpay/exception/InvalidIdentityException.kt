package net.hypedmc.picpay.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail

class InvalidIdentityException(
    private val detail: String
) : PicPayException() {

    override fun toProblemDetail(): ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        problemDetail.title = "Invalid identity"
        problemDetail.detail = detail
        return problemDetail
    }
}
