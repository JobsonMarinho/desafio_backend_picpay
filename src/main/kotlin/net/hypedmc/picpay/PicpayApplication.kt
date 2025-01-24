package net.hypedmc.picpay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class PicpayApplication

fun main(args: Array<String>) {
	runApplication<PicpayApplication>(*args)
}
