package com.remotex.feature.di.pullrequestlist

import com.remotex.domain.pullrequestlist.interfaces.PullrequestRepository
import com.remotex.feature.presentation.screens.pullrequestlist.PullrequestListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PullsViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideViewModel(repository: PullrequestRepository): PullrequestListViewModel {
        return PullrequestListViewModel(repository)
    }
}