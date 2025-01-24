package net.hypedmc.picpay.service.validation

interface Validator<T> {
    fun validate(data: T)
}