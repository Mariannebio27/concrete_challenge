package com.mariannecunha.concretechallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariannecunha.concretechallenge.data.PullRequestRepository
import com.mariannecunha.concretechallenge.data.RepositoryRepository
import com.mariannecunha.concretechallenge.model.PullRequest
import com.mariannecunha.concretechallenge.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestListViewModel(val repository: PullRequestRepository) : ViewModel() {

    private val _pullsLiveData = MutableLiveData<List<PullRequest>>()
    val pullsLiveData: LiveData<List<PullRequest>> = _pullsLiveData

    fun fetchPullRequests(repository: Repository?) {
        viewModelScope.launch(Dispatchers.IO) {
            val pulls = this@PullRequestListViewModel.repository.fetchPullRequests(repository)
            _pullsLiveData.postValue(pulls)
        }
    }
}