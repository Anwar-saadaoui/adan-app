package com.praytimeadan.adan.Components

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun convertLongToDate(timeInMillis:Long): Date {
        return Date(timeInMillis)
    }
    @TypeConverter
    fun convertDateToLong(date: Date):Long{
        return date.time
    }

}