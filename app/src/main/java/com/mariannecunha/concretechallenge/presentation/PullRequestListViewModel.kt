package com.mariannecunha.concretechallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariannecunha.concretechallenge.data.PullRequestRepositoryImpl
import com.mariannecunha.concretechallenge.domain.model.PullRequest
import com.mariannecunha.concretechallenge.domain.model.Repository
import com.mariannecunha.concretechallenge.domain.usecase.FetchPullRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestListViewModel(private val fetchPullRequests : FetchPullRequests) : ViewModel() {

    private val _pullsLiveData = MutableLiveData<List<PullRequest>>()
    val pullsLiveData: LiveData<List<PullRequest>> = _pullsLiveData

    fun getPullRequests(repository: Repository?) {
        viewModelScope.launch(Dispatchers.IO) {
            val pulls = fetchPullRequests(repository)
            _pullsLiveData.postValue(pulls)
        }
    }
}