package com.remotex.feature.di.repositorylist

import com.remotex.data.repositorylist.mapper.ReposMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ReposMapperModule {

    @Provides
    fun provideMapper(): ReposMapper {
        return ReposMapper()
    }
}