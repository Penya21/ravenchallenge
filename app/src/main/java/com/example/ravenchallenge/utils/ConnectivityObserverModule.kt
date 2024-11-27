package com.example.ravenchallenge.utils

import android.content.Context
import androidx.room.Room
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
object ConnectivityObserverModule {

    @Provides
    @Singleton
    fun providesConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver {
        return AndroidConnectivityObserver(context)
    }

}