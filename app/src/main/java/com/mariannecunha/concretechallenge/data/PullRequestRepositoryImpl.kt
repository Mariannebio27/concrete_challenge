package com.mariannecunha.concretechallenge.data

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.mariannecunha.concretechallenge.domain.model.PullRequest
import com.mariannecunha.concretechallenge.domain.model.Repository
import com.mariannecunha.concretechallenge.domain.repository.PullRequestRepository
import java.lang.Exception

class PullRequestRepositoryImpl(private val service: PullRequestService): PullRequestRepository {

    override suspend fun fetchPullRequests(repository: Repository?): List<PullRequest>? {
        val username = repository!!.owner.login
        val repoName = repository.name
        val result: SuspendableResult<List<PullRequest>, Exception> =
            SuspendableResult.of {
                service.fetchPullRequests(username, repoName)
            }

        return result.component1()
    }
}