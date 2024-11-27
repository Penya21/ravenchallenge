package com.example.ravenchallenge.utils

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.formatDate(): String{
    val givenDate = Instant.parse(this)
    val currentDate = Instant.now()
    val diff = Duration.between(givenDate, currentDate)
    val hours = diff.toHours()
    val minutes = diff.toMinutes()
    return if(hours > 0) "$hours h" else "$minutes m".replace(" ", "")
}
