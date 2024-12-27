package com.remotex.data.pullrequestlist.mapper

import com.remotex.data.pullrequestlist.dto.PullRequestDTO
import com.remotex.domain.pullrequestlist.models.PullrequestModel
import com.remotex.domain.pullrequestlist.models.PullrequestUserModel

class PullsMapper {
    fun toPullsModel(pullsDTO: List<PullRequestDTO>): List<PullrequestModel> {
        return pullsDTO.map {
            PullrequestModel(
                title = it.title,
                body = it.body,
                createdAt = it.createdAt,
                htmlUrl = it.htmlUrl,
                user = PullrequestUserModel(
                    login = it.user.login,
                    avatarUrl = it.user.avatarUrl
                )
            )
        }
    }
}