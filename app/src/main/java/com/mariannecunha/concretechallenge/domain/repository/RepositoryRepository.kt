package com.mariannecunha.concretechallenge.domain.repository

import com.mariannecunha.concretechallenge.domain.model.Repository

interface RepositoryRepository {

    suspend fun fetchRepositories(): List<Repository>?
}
