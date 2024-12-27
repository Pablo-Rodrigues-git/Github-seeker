package com.remotex.feature.pullrequestlist

import com.remotex.domain.pullrequestlist.models.PullrequestModel
import com.remotex.domain.pullrequestlist.models.PullrequestUserModel

object PullRequestTestHelper {
    fun getListOfPulls(): List<PullrequestModel> = listOf(
        PullrequestModel(
            title = "Pull request 1",
            body = "Descrição da pull request",
            createdAt = "2023-06-01T12:00:00Z",
            htmlUrl = "",
            user = PullrequestUserModel(
                login = "pablo-rodrigues-git",
                avatarUrl = "https://avatars.githubusercontent.com/u/385686000?v=4"
            )
        ),
        PullrequestModel(
            title = "Pull request 2",
            body = "Descrição da pull request",
            createdAt = "2023-06-02T12:00:00Z",
            htmlUrl = "",
            user = PullrequestUserModel(
                login = "user-2",
                avatarUrl = "https://avatars.githubusercontent.com/u/12345678?v=4"
            )
        )
    )
}
