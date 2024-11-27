package com.example.ravenchallenge.news_list.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class News(
    @ColumnInfo(defaultValue = "Anon")
    val author: String?,
    @ColumnInfo(defaultValue = "No Description")
    val comment_text: String?,
    val created_at: String,
    val created_at_i: Long,
    @ColumnInfo(defaultValue = "No Title")
    val story_title: String?,
    val story_id: Int,
    @ColumnInfo(defaultValue = "")
    val story_url: String?,
    @PrimaryKey(autoGenerate = false)
    val objectID: Int
): Parcelable

