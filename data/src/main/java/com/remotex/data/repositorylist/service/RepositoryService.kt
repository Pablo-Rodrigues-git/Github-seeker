package com.remotex.data.repositorylist.service

import com.remotex.data.repositorylist.dto.RepositoryItemsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String = QUERY_LANGUAGE,
        @Query("sort") sort: String = QUERY_SORT_BY_STARS,
        @Query("page") page: Int = QUERY_INIT_PAGE
    ): RepositoryItemsDTO

    companion object {
        private const val QUERY_LANGUAGE = "language:Java"
        private const val QUERY_SORT_BY_STARS = "stars"
        private const val QUERY_INIT_PAGE = 0
    }
}
