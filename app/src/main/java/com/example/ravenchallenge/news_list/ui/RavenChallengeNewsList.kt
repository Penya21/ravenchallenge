package com.example.ravenchallenge.news_list.ui

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
import com.example.ravenchallenge.R
import com.example.ravenchallenge.news_list.domain.model.News
import com.example.ravenchallenge.utils.formatDate
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RavenChallengeNewsList(
    homeUIState: HomeUIState,
    newsLazyListState: LazyListState,
    modifier: Modifier,
    navigateToDetail: (Int) -> Unit,
    refreshList: () -> Unit,
    onDelete: (News) -> Unit,
) {

    if(homeUIState.news.isEmpty()) {
        Text("Loading...",modifier = modifier.fillMaxSize(), textAlign = TextAlign.Center, color = Color.White, fontWeight = FontWeight.Bold)

    }else {
        PullToRefreshBox(
            onRefresh = { refreshList() },
            isRefreshing = homeUIState.isRefreshing
        )
        {

            LazyColumn(
                modifier = modifier,
                state = newsLazyListState,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                itemsIndexed(items = homeUIState.news, key = { index, item ->
                    item.objectID.toString()
                }) { index, item ->
                    RavenChallengeNewsListItem(
                        news = item,
                        homeUIState = homeUIState,
                        navigateToDetail = { navigateToDetail(item.story_id) },
                        onDelete = onDelete
                    )
                    if (index < homeUIState.news.lastIndex) {

                        HorizontalDivider(
                            color = Color.Black,
                            thickness = 1.dp
                        )
                    }
                }

            }
        }
    }


}


@Composable
fun RavenChallengeNewsListItem(
    news: News,
    homeUIState: HomeUIState,
    navigateToDetail: (Int) -> Unit,
    onDelete: (News) -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val dismissState = rememberSwipeToDismissBoxState()

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            val color by
            animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.Settled -> Color.LightGray
                    SwipeToDismissBoxValue.StartToEnd -> Color.LightGray
                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                }
            )

            when(dismissState.currentValue){
                SwipeToDismissBoxValue.EndToStart ->{
                    onDelete(news)
                }
                SwipeToDismissBoxValue.StartToEnd -> {
                }
                SwipeToDismissBoxValue.Settled -> {
                }
            }

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ){
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint= Color.White)
            }
        }
    ) {

        Card(shape = RoundedCornerShape(0.dp), elevation = CardDefaults.cardElevation(0.dp), modifier = modifier
            .height(100.dp)
            .clickable { navigateToDetail(news.story_id) }) {

            Box(modifier = modifier.fillMaxSize()) {

                Box(modifier = Modifier
                    .matchParentSize()
                    .background(colorResource(R.color.white)))

                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center)
                ){

                    Text(
                        text = news.story_title ?: "No Title",
                        style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text =  (news.author ?: "Anonymous").replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        } + " - " + (news.created_at.formatDate()),
                        style = TextStyle(color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Light)

                    )

                }

            }


        }

    }

}
