package com.mariannecunha.concretechallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariannecunha.concretechallenge.data.RepositoryRepository
import com.mariannecunha.concretechallenge.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryListViewModel(val repository: RepositoryRepository): ViewModel() {

    private val _productsLiveData = MutableLiveData<List<Repository>>()
    val productsLiveData: LiveData<List<Repository>> = _productsLiveData

    fun fetchRepositories(){
        viewModelScope.launch(Dispatchers.IO) {
            val products = repository.fetchRepositories()
            _productsLiveData.postValue(products)
        }
    }
}