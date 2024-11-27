package com.example.ravenchallenge.news_list.data.local
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ravenchallenge.news_list.domain.model.BlacklistedNews
import com.example.ravenchallenge.news_list.domain.model.News


@Database(
    entities = [News::class, BlacklistedNews::class],
    version = 1
)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun dao(): NewsDao

}