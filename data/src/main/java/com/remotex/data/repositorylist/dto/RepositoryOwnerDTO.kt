package com.remotex.data.repositorylist.dto

import com.google.gson.annotations.SerializedName

data class RepositoryOwnerDTO(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
)
