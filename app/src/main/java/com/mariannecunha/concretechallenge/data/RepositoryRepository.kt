package com.mariannecunha.concretechallenge.data

import com.github.kittinunf.result.coroutines.SuspendableResult
import com.mariannecunha.concretechallenge.model.GlobalRepository
import com.mariannecunha.concretechallenge.model.Repository
import java.lang.Exception

class RepositoryRepository(private val service: RepositoryService) {

    private var currentPageNumber = 1

    suspend fun fetchRepositories(): List<Repository>? {

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