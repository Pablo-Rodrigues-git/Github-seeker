package com.remotex.data.pullrequestlist.dto

import com.google.gson.annotations.SerializedName

data class PullrequestUserDTO(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
