package com.remotex.data.pullrequestlist.mapper

import com.remotex.data.pullrequestlist.dto.PullRequestDTO
import com.remotex.domain.pullrequestlist.models.PullrequestModel

interface PullsMapper {
    fun toPullsModel(pullsDTO: List<PullRequestDTO>): List<PullrequestModel>
}
