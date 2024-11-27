package com.example.ravenchallenge.news_list.data.repository

import com.example.ravenchallenge.news_list.data.local.NewsDao
import com.example.ravenchallenge.news_list.domain.model.BlacklistedNews
import com.example.ravenchallenge.news_list.domain.model.News
import com.example.ravenchallenge.news_list.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(
    private val dao: NewsDao
) : DatabaseRepository{
    override suspend fun upsertNews(news: News) {
        dao.upsertNews(news)
    }

    override suspend fun deleteNews(news: News) {
        dao.deleteNews(news)
    }

    override suspend fun updateNews(news: News) {
        dao.updateNews(news)
    }

    override suspend fun blacklistNews(blacklistedNews: BlacklistedNews) {
        dao.blacklistNews(blacklistedNews)
    }

    override fun getNewsOrderedByDate(): Flow<List<News>> {
        return dao.getNewsOrderedByDate()
    }


}