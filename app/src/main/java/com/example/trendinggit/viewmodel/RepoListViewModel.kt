package com.example.trendinggit.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.trendinggit.model.Item
import com.example.trendinggit.model.TrendingRequest
import com.example.trendinggit.viewmodel.base.BaseViewModel

class RepoListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<List<Item>>()

    fun fetchRepoData() {
        dataLoading.value = true
        TrendingRequest.getInstance().getRepoList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                repoListLive.value = response?.items
                empty.value = false
            }else {
                empty.value = true
            }
        }
    }

}