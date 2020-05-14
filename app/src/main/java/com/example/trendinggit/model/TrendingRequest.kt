package com.example.trendinggit.model

import com.example.trendinggit.model.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingRequest {

    companion object {
        private var INSTANCE : TrendingRequest? = null
        fun getInstance() = INSTANCE ?: TrendingRequest().also { INSTANCE = it }
    }

    fun getRepoList(onResult: (isSuccess: Boolean, response: GitResponse?) -> Unit) {

        ApiClient.instance.getRepo().enqueue(object : Callback<GitResponse> {
            override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>?) {
                if (response != null && response.isSuccessful) {
                    onResult(true, response.body())
                }else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                onResult(false, null)
            }
        })

    }
}