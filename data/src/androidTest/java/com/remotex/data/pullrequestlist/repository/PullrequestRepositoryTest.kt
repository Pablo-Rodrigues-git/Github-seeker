package com.remotex.data.pullrequestlist.repository

import com.remotex.data.pullrequestlist.mapper.PullsMapper
import com.remotex.data.pullrequestlist.service.PullsService
import com.remotex.domain.pullrequestlist.models.PullrequestModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PullrequestRepositoryTest {

    private val service: PullsService = mockk()
    private val mapper: PullsMapper = mockk()
    private val pullsRepository = PullrequestRepositoryImpl(service, mapper)

    @Test
    fun `pull-request-repository-test`(): Unit = runTest {
        coEvery { service.getPulls(any(), any()) } returns listOf()
        every { mapper.toPullsModel(any()) } returns listOf()

        val expected = listOf<PullrequestModel>()
        val result = pullsRepository.getPulls("test", "test")

        assert(result == expected)
    }
}