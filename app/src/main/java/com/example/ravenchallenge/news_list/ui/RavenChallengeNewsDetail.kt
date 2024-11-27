package com.example.ravenchallenge.news_list.ui

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ravenchallenge.R
import com.example.ravenchallenge.news_list.domain.model.News

@Composable
fun RavenChallengeNewsDetail(
    news: News,
    modifier: Modifier
) {

    Column(modifier = modifier
        .padding(top = 100.dp)
        .fillMaxSize()
        .background(colorResource(R.color.white))) {

        RavenChallengeWebView(news = news, modifier = modifier)


    }


}


@Composable
fun RavenChallengeWebView(
    news: News,
    modifier: Modifier
){

    if(!news.story_url.isNullOrEmpty()){

        // Adding a WebView inside AndroidView
        // with layout as full screen
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }, update = {
            it.loadUrl(news.story_url)
        }, modifier = Modifier.fillMaxSize())


    }
}