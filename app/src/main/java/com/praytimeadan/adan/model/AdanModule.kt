package com.praytimeadan.adan.model



data class PrayerTimingsResponse(
    val data: List<PrayerTimings>
)


data class PrayerTimings(
    val timings: Timings,
    val date: Date,
    val meta: Meta
)

data class Timings(
    val Fajr: String,
    val Sunrise: String,
    val Dhuhr: String,
    val Asr: String,
    val Sunset: String,
    val Maghrib: String,
    val Isha: String,
    val Imsak: String,
    val Midnight: String,
    val Firstthird: String,
    val Lastthird: String
)

data class Date(
    val readable: String,
    val timestamp: String,
    val gregorian: Gregorian,
    val hijri: Hijri
)

data class Gregorian(
    val date: String,
    val format: String,
    val day: String,
    val weekday: Weekday,
    val month: Month,
    val year: String,
    val designation: Designation
)

data class Weekday(
    val en: String
)

data class Month(
    val number: Int,
    val en: String
)

data class Designation(
    val abbreviated: String,
    val expanded: String
)

data class Hijri(
    val date: String,
    val format: String,
    val day: String,
    val weekday: Weekday,
    val month: Month,
    val year: String,
    val designation: Designation,
    val holidays: List<Any>
)

data class Meta(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val method: Method,
    val latitudeAdjustmentMethod: String,
    val midnightMode: String,
    val school: String,
    val offset: Offset
)

data class Method(
    val id: Int,
    val name: String,
    val params: Params,
    val location: Location
)

data class Params(
    val Fajr: Int,
    val Isha: Int
)

data class Location(
    val latitude: Double,
    val longitude: Double
)

data class Offset(
    val Imsak: Int,
    val Fajr: Int,
    val Sunrise: Int,
    val Dhuhr: Int,
    val Asr: Int,
    val Maghrib: Int,
    val Sunset: Int,
    val Isha: Int,
    val Midnight: Int
)
