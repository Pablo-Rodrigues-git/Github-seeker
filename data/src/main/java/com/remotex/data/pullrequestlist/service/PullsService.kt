package com.remotex.data.pullrequestlist.service

import com.remotex.data.pullrequestlist.dto.PullRequestDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PullsService {

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPulls(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<PullRequestDTO>
}