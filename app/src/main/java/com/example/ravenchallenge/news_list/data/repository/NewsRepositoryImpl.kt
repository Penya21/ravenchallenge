package com.example.ravenchallenge.news_list.data.repository

import com.example.ravenchallenge.news_list.data.remote.ApiService
import com.example.ravenchallenge.news_list.domain.model.NewsResponse
import com.example.ravenchallenge.news_list.domain.repository.NewsRepository
import com.example.ravenchallenge.utils.SnackbarController
import com.example.ravenchallenge.utils.SnackbarEvent


class NewsRepositoryImpl(
    private val api: ApiService
) : NewsRepository {

    override suspend fun getNews(): NewsResponse{

        val response = api.getNews()
        if(response.isSuccessful){
            return response.body() ?: NewsResponse(emptyList(), 0, 0)
        }else{
            val errorMsg = response.errorBody()?.string()
            SnackbarController.sendEvents(SnackbarEvent("$errorMsg", null))
            return NewsResponse(emptyList(), 0, 0)
        }
    }

}