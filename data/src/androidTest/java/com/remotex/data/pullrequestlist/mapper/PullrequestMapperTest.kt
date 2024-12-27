package com.remotex.data.pullrequestlist.mapper

import com.remotex.data.pullrequestlist.dto.PullRequestDTO
import com.remotex.domain.pullrequestlist.models.PullrequestModel
import org.junit.Test

class PullrequestMapperTest {

    private val mapper = PullsMapper()

    @Test
    fun `test-repositoryList-mapper`() {
        val mappedPulls = mapper.toPullsModel(getPullsItemsDTO())
        val expectedPulls = getPullsItemsModel()
        assert(mappedPulls == expectedPulls)
    }

    private fun getPullsItemsDTO(): List<PullRequestDTO> = listOf()

    private fun getPullsItemsModel(): List<PullrequestModel> = listOf()
}