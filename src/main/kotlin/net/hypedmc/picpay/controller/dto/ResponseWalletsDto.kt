package net.hypedmc.picpay.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseWalletsDto(

    @JsonProperty("content")
    val content: List<ResponseWalletDto>,

    @JsonProperty("total_pages")
    val totalPages: Int,

    @JsonProperty("total_elements")
    val totalElements: Long,

    @JsonProperty("current_page")
    val currentPage: Int,

    @JsonProperty("per_page")
    val perPage: Int,

    @JsonProperty("sort")
    val sort: String
)