package com.mariannecunha.concretechallenge.presentation.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariannecunha.concretechallenge.domain.model.Repository
import com.mariannecunha.concretechallenge.domain.usecase.FetchRepositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryListViewModel(private val fetchRepositories: FetchRepositories) : ViewModel() {

    private val _productsLiveData = MutableLiveData<List<Repository>>()
    val productsLiveData: LiveData<List<Repository>> = _productsLiveData

    fun getRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            val products = fetchRepositories()
            _productsLiveData.postValue(products)
        }
    }
}
