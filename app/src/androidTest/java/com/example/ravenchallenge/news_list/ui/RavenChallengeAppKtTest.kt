package com.example.ravenchallenge.news_list.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.platform.app.InstrumentationRegistry
import com.example.ravenchallenge.MainActivity
import com.example.ravenchallenge.news_list.data.di.ApiModule
import com.example.ravenchallenge.news_list.data.di.DatabaseModule
import com.example.ravenchallenge.news_list.data.di.TestDatabaseModule
import com.example.ravenchallenge.news_list.data.local.NewsDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@HiltAndroidTest
@UninstallModules(DatabaseModule::class, ApiModule::class)
class RavenChallengeAppKtTest{



    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)


    @Before
    fun setUp(){
        hiltRule.inject()
    }



}