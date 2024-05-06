package com.praytimeadan.adan.retrofit

import com.praytimeadan.adan.model.PrayerTimingsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AdanInterface {
    @GET("v1/calendar/{year}/{month}")
    suspend fun getTiming(
        @Path("year") year:Int,
        @Path("month") month:Int,
        @QueryMap params: Map<String, String>
    ): PrayerTimingsResponse
}