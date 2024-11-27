package com.example.ravenchallenge.news_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BlacklistedNews (
    @PrimaryKey(autoGenerate = false)
    val objectID: Int
)