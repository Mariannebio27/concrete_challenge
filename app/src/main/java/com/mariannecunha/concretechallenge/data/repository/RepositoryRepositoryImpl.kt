package com.mariannecunha.concretechallenge.data.repository

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.mariannecunha.concretechallenge.domain.model.GlobalRepository
import com.mariannecunha.concretechallenge.domain.model.Repository
import com.mariannecunha.concretechallenge.domain.repository.RepositoryRepository
import java.lang.Exception

class RepositoryRepositoryImpl(private val service: RepositoryService) : RepositoryRepository {

    private var currentPageNumber = 1

    override suspend fun fetchRepositories(): List<Repository>? {

        val result: SuspendableResult<GlobalRepository, Exception> =
            SuspendableResult.of {
                service.fetchRepositories(page = currentPageNumber)
            }
        updatePage()

        return result.component1()?.repositories
    }

    private fun updatePage() {
        currentPageNumber += 1
    }
}
