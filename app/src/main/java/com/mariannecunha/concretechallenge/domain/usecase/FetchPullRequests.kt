package com.mariannecunha.concretechallenge.domain.usecase

import com.mariannecunha.concretechallenge.domain.model.PullRequest
import com.mariannecunha.concretechallenge.domain.model.Repository
import com.mariannecunha.concretechallenge.domain.repository.PullRequestRepository

class FetchPullRequests(private val pullRequestRepository: PullRequestRepository) {

    suspend operator fun invoke(repository: Repository?): List<PullRequest>? {
        return pullRequestRepository.fetchPullRequests(repository)
    }
}
