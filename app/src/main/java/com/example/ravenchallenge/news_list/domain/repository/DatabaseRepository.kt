package com.example.ravenchallenge.news_list.domain.repository

import com.example.ravenchallenge.news_list.domain.model.BlacklistedNews
import com.example.ravenchallenge.news_list.domain.model.News
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    suspend fun upsertNews(news: News)

    suspend fun deleteNews(news: News)

    suspend fun updateNews(news: News)

    suspend fun blacklistNews(blacklistedNews: BlacklistedNews)

    fun getNewsOrderedByDate(): Flow<List<News>>
}