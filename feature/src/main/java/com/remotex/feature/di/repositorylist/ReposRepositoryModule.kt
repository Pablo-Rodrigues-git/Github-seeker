package com.remotex.feature.di.repositorylist

import com.remotex.data.repositorylist.mapper.ReposMapper
import com.remotex.data.repositorylist.repository.ReposRepositoryImpl
import com.remotex.data.repositorylist.service.RepositoryService
import com.remotex.domain.repositorylist.interfaces.ReposRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ReposRepositoryModule {

    @Provides
    fun provideRepository(service: RepositoryService, mapper: ReposMapper): ReposRepository {
        return ReposRepositoryImpl(
            service,
            mapper
        )
    }
}