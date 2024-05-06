package com.praytimeadan.adan.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBTimings(
    @PrimaryKey val currentDate: String,
    val Fajr: String,
    val Sunrise: String,
    val Dhuhr: String,
    val Asr: String,
    val Maghrib: String,
    val Isha: String,
)

@Entity
data class DBDate(
    @PrimaryKey val hijri: String,
    val gregorian: String,
)
