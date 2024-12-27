package com.remotex.data.repositorylist.dto

import com.google.gson.annotations.SerializedName

data class RepositoryItemsDTO(
    @SerializedName("items") val items: List<RepositoryDTO>
)
