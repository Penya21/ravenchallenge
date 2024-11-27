package com.example.ravenchallenge.news_list.data.di


import android.content.Context
import androidx.room.Room
import com.example.ravenchallenge.news_list.data.local.NewsDao
import com.example.ravenchallenge.news_list.data.local.NewsDatabase
import com.example.ravenchallenge.news_list.data.repository.DatabaseRepositoryImpl
import com.example.ravenchallenge.news_list.domain.repository.DatabaseRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.inMemoryDatabaseBuilder(
            context,
            NewsDatabase::class.java)
            .build()

    @Provides
    @Singleton
    fun provideDatabaseRepository(db: NewsDatabase): DatabaseRepository {
        return DatabaseRepositoryImpl(db.dao())
    }
}