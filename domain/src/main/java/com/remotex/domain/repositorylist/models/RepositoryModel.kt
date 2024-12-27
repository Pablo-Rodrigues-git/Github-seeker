package com.remotex.domain.repositorylist.models

data class RepositoryModel(
    val name: String,
    val description: String,
    val stargazersCount: Int,
    val forksCount: Int,
    val owner: RepositoryOwnerModel
)
