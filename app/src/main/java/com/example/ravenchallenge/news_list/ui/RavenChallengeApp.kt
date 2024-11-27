package com.example.ravenchallenge.news_list.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ravenchallenge.news_list.domain.model.News

@Composable
fun RavenChallengeApp(
    homeUIState: HomeUIState,
    closeDetailScreen: () -> Unit = {},
    navigateToDetail: (Int) -> Unit = {},
    modifier: Modifier,
    refreshList:() -> Unit,
    onDelete: (News) -> Unit,
    ) {
    RavenChallengeAppContent(
        homeUIState = homeUIState,
        closeDetailScreen = closeDetailScreen,
        navigateToDetail = navigateToDetail,
        modifier = modifier,
        refreshList = refreshList,
        onDelete = onDelete
    )
}

@Composable
fun RavenChallengeAppContent(
    modifier: Modifier,
    homeUIState: HomeUIState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    onDelete: (News) -> Unit,
    refreshList:() -> Unit) {


    Column(
        modifier = Modifier.fillMaxSize()
    ){
        RavenChallengeNewsScreen(
            homeUIState = homeUIState,
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail,
            modifier = modifier,
            refreshList = refreshList,
            onDelete = onDelete
        )
    }


}