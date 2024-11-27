package com.example.ravenchallenge.news_list.data.remote

import com.example.ravenchallenge.news_list.domain.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("search_by_date?query=android")
    suspend fun getNews(): Response<NewsResponse>


}