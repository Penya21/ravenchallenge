package com.example.ravenchallenge


import android.content.Context

import androidx.compose.ui.platform.LocalContext
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ravenchallenge.news_list.ui.NewsViewModel
import com.example.ravenchallenge.news_list.ui.RavenChallengeApp
import com.example.ravenchallenge.ui.theme.MyApplicationTheme
import com.example.ravenchallenge.utils.ObserveAsEvents
import com.example.ravenchallenge.utils.SnackbarController

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<NewsViewModel>()
            val isConnected by viewModel.isConnected.collectAsState()


            if (isConnected) {
                viewModel.loadNews()
            } else {
                viewModel.onConnectionMissing()
            }


            val uiState by viewModel.uiState.collectAsState()

            MyApplicationTheme {

                val snackbarHostState = remember {
                    SnackbarHostState()
                }

                val scope = rememberCoroutineScope()
                ObserveAsEvents(
                    flow = SnackbarController.events,
                    snackbarHostState) { event ->
                    scope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        val result = snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action?.name,
                            duration = SnackbarDuration.Short
                        )

                        if(result == SnackbarResult.ActionPerformed){
                             event.action?.action?.invoke()
                        }
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = {SnackbarHost(
                        hostState = snackbarHostState
                    )},
                    topBar = {
                    if(!uiState.isDetailOpen) {
                        MainTopBar()
                    }else{
                        DetailTopBar(viewModel = viewModel, context = LocalContext.current)
                    }

                }, content = { innerPadding ->
                    RavenChallengeApp(
                        homeUIState = uiState,
                        closeDetailScreen = {
                            viewModel.closeDetailScreen()
                        },
                        navigateToDetail = { id->
                            viewModel.setSelectedNews(id)
                        },
                        refreshList = {
                            if (isConnected) {
                                viewModel.loadNews()
                            } else {
                                viewModel.onConnectionMissing()
                            }
                        },
                        onDelete = {
                            viewModel.blackListItem(it)
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(colorResource(R.color.white))
                    )
                })
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.purple_500),
            titleContentColor = colorResource(R.color.purple_200),
        ),
        title = {
            Text("Raven Challenge")

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(modifier: Modifier = Modifier, viewModel: NewsViewModel, context: Context) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.purple_500),
            titleContentColor = colorResource(R.color.purple_200),
        ),
        title = { Text("") },
        navigationIcon = {
            IconButton(onClick = { viewModel.closeDetailScreen() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Button",
                    tint = colorResource(R.color.purple_200)
                )
            }
        },
        actions = {},
        scrollBehavior = scrollBehavior,
    )
}


