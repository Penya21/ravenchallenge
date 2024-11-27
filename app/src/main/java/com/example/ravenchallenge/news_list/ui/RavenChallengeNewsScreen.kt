package com.example.ravenchallenge.news_list.ui



import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ravenchallenge.R
import com.example.ravenchallenge.news_list.domain.model.News
import com.example.ravenchallenge.utils.formatDate
import java.util.Locale

@Composable
fun RavenChallengeNewsScreen(

    homeUIState: HomeUIState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    refreshList: () -> Unit,
    onDelete: (News) -> Unit,
    modifier: Modifier
) {
    val newsLazyListState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()){

        RavenChallengeNewsListContent(
            homeUIState = homeUIState,
            newsLazyListState = newsLazyListState,
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail,
            refreshList = refreshList,
            onDelete = onDelete,
            modifier = modifier
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RavenChallengeNewsListContent(
    homeUIState: HomeUIState,
    newsLazyListState: LazyListState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    refreshList: () -> Unit,
    onDelete: (News) -> Unit,
    modifier: Modifier) {

    if(homeUIState.selectedNews != null && homeUIState.isDetailOpen){
        BackHandler {
            closeDetailScreen()
        }

        RavenChallengeNewsDetail(
            news = homeUIState.selectedNews,
            modifier = Modifier
        )

    }else{
        RavenChallengeNewsList(
            homeUIState = homeUIState,
            newsLazyListState = newsLazyListState,
            modifier = modifier.padding(top = 0.dp),
            navigateToDetail = navigateToDetail,
            refreshList = refreshList,
            onDelete = onDelete)
    }

}


