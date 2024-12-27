package com.remotex.data.pullrequestlist.dto

import com.google.gson.annotations.SerializedName

data class PullRequestDTO(
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("user") val user: PullrequestUserDTO
)
