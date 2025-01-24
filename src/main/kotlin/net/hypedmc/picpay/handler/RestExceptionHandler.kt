package net.hypedmc.picpay.handler

import feign.FeignException
import net.hypedmc.picpay.exception.PicPayException
import org.springframework.data.mapping.PropertyReferenceException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

data class InvalidParam(val name: String, val reason: String)

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(PicPayException::class)
    fun handlePicPayException(exception: PicPayException) : ProblemDetail {
        return exception.toProblemDetail()
    }

    @ExceptionHandler(PropertyReferenceException::class)
    fun handlePropertyReferenceException(exception: PropertyReferenceException) : ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)
        problemDetail.title = "Bad request"
        problemDetail.detail = exception.message
        return problemDetail
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(exception: MethodArgumentTypeMismatchException) : ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)
        problemDetail.title = "Bad request"
        problemDetail.detail = "The provided ${exception.value} is invalid"
        return problemDetail
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ProblemDetail {
        val fieldErrors = e.fieldErrors
            .stream()
            .map<Any> { f: FieldError -> InvalidParam(f.field, f.defaultMessage ?: "") }
            .toList()

        val pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)

        pb.title = "Some fields are invalid"
        pb.setProperty("invalid-params", fieldErrors)

        return pb
    }

    @ExceptionHandler(FeignException::class)
    fun handleFeignException(e: FeignException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatus(HttpStatus.SERVICE_UNAVAILABLE)
        problemDetail.title = "Service unavailable"
        if (e.cause != null) {
            problemDetail.detail = e.cause?.message
        }
        return problemDetail
    }
}