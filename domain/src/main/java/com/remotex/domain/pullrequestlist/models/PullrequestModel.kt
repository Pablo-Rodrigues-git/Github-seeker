package com.remotex.domain.pullrequestlist.models

data class PullrequestModel(
    val title: String,
    val body: String?,
    val createdAt: String,
    val htmlUrl: String,
    val user: PullrequestUserModel
)
