package com.example.ravenchallenge.news_list.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.ravenchallenge.news_list.domain.model.BlacklistedNews
import com.example.ravenchallenge.news_list.domain.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Upsert
    suspend fun upsertNews(news: News)

    @Delete
    suspend fun deleteNews(news: News)

    @Update
    suspend fun updateNews(news: News)

    @Insert
    suspend fun blacklistNews(blacklistedNews: BlacklistedNews)

    @Query("SELECT * FROM news WHERE NOT EXISTS (SELECT 1 FROM blacklistednews WHERE blacklistednews.objectID == news.objectID) ORDER BY created_at_i DESC")
    fun getNewsOrderedByDate(): Flow<List<News>>
}