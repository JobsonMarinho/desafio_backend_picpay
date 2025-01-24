package net.hypedmc.picpay.client

import net.hypedmc.picpay.entity.Transfer
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "notification",
    url = "\${client.notify.url}"
)
interface NotificationClient {

    @PostMapping
    fun notify(@RequestBody transfer: Transfer): ResponseEntity<Unit>

}