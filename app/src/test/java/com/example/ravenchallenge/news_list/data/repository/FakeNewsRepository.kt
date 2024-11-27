package com.example.ravenchallenge.news_list.data.repository

import com.example.ravenchallenge.news_list.data.local.NewsDao
import com.example.ravenchallenge.news_list.domain.model.News
import com.example.ravenchallenge.news_list.domain.model.NewsResponse
import com.example.ravenchallenge.news_list.domain.repository.NewsRepository

class FakeNewsRepository : NewsRepository {

    private val news = mutableListOf<News>()


    override suspend fun getNews(): NewsResponse {
      return NewsResponse(news, 20, 0)
    }

}