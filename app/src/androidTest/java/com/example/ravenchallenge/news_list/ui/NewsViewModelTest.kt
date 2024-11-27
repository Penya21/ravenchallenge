package com.example.ravenchallenge.news_list.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.platform.app.InstrumentationRegistry
import com.example.ravenchallenge.MainActivity
import com.example.ravenchallenge.news_list.data.di.ApiModule
import com.example.ravenchallenge.news_list.data.di.DatabaseModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock

@HiltAndroidTest
@UninstallModules(DatabaseModule::class, ApiModule::class)
class NewsViewModelTest{

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Mock
    private lateinit var userViewModel: NewsViewModel


    @Before
    fun setUp(){
        hiltRule.inject()
        composeRule.setContent {
            userViewModel = hiltViewModel<NewsViewModel>()
        }
    }



}