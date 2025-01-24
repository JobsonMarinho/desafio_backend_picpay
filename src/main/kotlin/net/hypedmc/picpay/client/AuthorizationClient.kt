package net.hypedmc.picpay.client

import net.hypedmc.picpay.client.dto.AuthorizationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
   name = "authorization",
   url = "\${client.authorization.url}"
)
interface AuthorizationClient {

    @GetMapping
    fun isAuthorized(): ResponseEntity<AuthorizationResponse>

}