package net.hypedmc.picpay.service.validation

import net.hypedmc.picpay.controller.dto.CreateWalletDto
import org.springframework.stereotype.Component
import net.hypedmc.picpay.exception.InvalidIdentityException

@Component
class IdentityValidator : Validator<CreateWalletDto> {
    override fun validate(data: CreateWalletDto) {
        val identity = data.identity.filter { it.isDigit() }

        if (!isValidIdentity(identity)) {
            throw InvalidIdentityException("The provided CPF or CNPJ is invalid: ${data.identity}")
        }
    }

    private fun isValidIdentity(identity: String): Boolean {
        return when (identity.length) {
            11 -> isValidCpf(identity)
            14 -> isValidCnpj(identity)
            else -> false
        }
    }

    fun isValidCpf(cpf: String): Boolean {
        if (cpf.length != 11 || cpf.all { it == cpf[0] }) return false
        val digits = cpf.map { it.toString().toInt() }
        val firstVerifier = calculateCpfVerifier(digits.subList(0, 9), 10)
        if (firstVerifier != digits[9]) return false
        val secondVerifier = calculateCpfVerifier(digits.subList(0, 10), 11)
        return secondVerifier == digits[10]
    }

    private fun calculateCpfVerifier(digits: List<Int>, weight: Int): Int {
        val sum = digits.mapIndexed { index, digit -> digit * (weight - index) }.sum()
        val remainder = sum % 11
        return if (remainder < 2) 0 else 11 - remainder
    }

    fun isValidCnpj(cnpj: String): Boolean {
        if (cnpj.length != 14 || cnpj.all { it == cnpj[0] }) return false
        val digits = cnpj.map { it.toString().toInt() }
        val firstVerifier = calculateCnpjVerifier(digits.subList(0, 12), intArrayOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2))
        if (firstVerifier != digits[12]) return false
        val secondVerifier = calculateCnpjVerifier(digits.subList(0, 13), intArrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2))
        return secondVerifier == digits[13]
    }

    private fun calculateCnpjVerifier(digits: List<Int>, weights: IntArray): Int {
        val sum = digits.mapIndexed { index, digit -> digit * weights[index] }.sum()
        val remainder = sum % 11
        return if (remainder < 2) 0 else 11 - remainder
    }
}
