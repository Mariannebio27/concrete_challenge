package com.mariannecunha.concretechallenge.domain.usecase

import com.mariannecunha.concretechallenge.domain.model.Repository
import com.mariannecunha.concretechallenge.domain.repository.RepositoryRepository

class FetchRepositories(private val repository: RepositoryRepository) {

    suspend operator fun invoke(): List<Repository>? {
        return repository.fetchRepositories()
    }
}
