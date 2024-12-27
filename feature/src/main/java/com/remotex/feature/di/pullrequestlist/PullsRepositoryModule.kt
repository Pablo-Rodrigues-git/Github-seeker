package com.remotex.feature.di.pullrequestlist

import com.remotex.data.pullrequestlist.mapper.PullsMapper
import com.remotex.data.pullrequestlist.repository.PullrequestRepositoryImpl
import com.remotex.data.pullrequestlist.service.PullsService
import com.remotex.domain.pullrequestlist.interfaces.PullrequestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PullsRepositoryModule {

    @Provides
    fun provideRepository(service: PullsService, mapper: PullsMapper): PullrequestRepository {
        return PullrequestRepositoryImpl(service, mapper)
    }
}