package com.example.ravenchallenge.news_list.domain.repository

import com.example.ravenchallenge.news_list.domain.model.NewsResponse
import retrofit2.Response


interface NewsRepository {
    suspend fun getNews() : NewsResponse


}