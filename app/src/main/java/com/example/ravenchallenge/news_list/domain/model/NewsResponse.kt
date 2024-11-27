package com.example.ravenchallenge.news_list.domain.model


data class NewsResponse (
    val hits: List<News>,
    val hitsPerPage: Int,
    val page: Int
)