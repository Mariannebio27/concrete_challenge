package com.mariannecunha.concretechallenge.presentation.di

import com.mariannecunha.concretechallenge.data.PullRequestRepository
import com.mariannecunha.concretechallenge.data.RepositoryRepository
import com.mariannecunha.concretechallenge.presentation.PullRequestListAdapter
import com.mariannecunha.concretechallenge.presentation.PullRequestListViewModel
import com.mariannecunha.concretechallenge.presentation.RepositoryListAdapter
import com.mariannecunha.concretechallenge.presentation.RepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.http.GET

val presentationModule = module {

    viewModel {
        RepositoryListViewModel(
            get<RepositoryRepository>()
        )
    }

    viewModel {
        PullRequestListViewModel(
            get<PullRequestRepository>()
        )
    }

    factory {
        PullRequestListAdapter()
    }

    factory {
        RepositoryListAdapter()
    }


}