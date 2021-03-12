package com.mariannecunha.concretechallenge.domain.di

import com.mariannecunha.concretechallenge.domain.repository.PullRequestRepository
import com.mariannecunha.concretechallenge.domain.repository.RepositoryRepository
import com.mariannecunha.concretechallenge.domain.usecase.FetchPullRequests
import com.mariannecunha.concretechallenge.domain.usecase.FetchRepositories
import org.koin.dsl.module

val domainModule = module {

    factory {
        FetchRepositories(
            get<RepositoryRepository>()
        )
    }

    factory {
        FetchPullRequests(
            get<PullRequestRepository>()
        )
    }
}
