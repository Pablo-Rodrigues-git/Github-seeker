package com.remotex.feature.di.pullrequestlist

import com.remotex.data.pullrequestlist.mapper.PullsMapper
import com.remotex.data.pullrequestlist.mapper.PullsMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PullsMapperModule {

    @Provides
    fun provideMapper(): PullsMapper {
        return PullsMapperImpl()
    }
}