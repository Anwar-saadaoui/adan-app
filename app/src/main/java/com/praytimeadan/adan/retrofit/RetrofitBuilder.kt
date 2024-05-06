package com.praytimeadan.adan.retrofit

import com.praytimeadan.adan.model.PrayerTimingsResponse
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder() {

    private val baseUrl = "https://api.aladhan.com/"
    private var adanInterface: AdanInterface
    companion object{
        private var INSTANCE:RetrofitBuilder = RetrofitBuilder()
        fun getINSTANCE():RetrofitBuilder{
            return INSTANCE
        }
    }


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        adanInterface = retrofit.create(AdanInterface::class.java)
    }



    suspend fun getTamingData(year:Int, month:Int, params:Map<String,String>):PrayerTimingsResponse{
        return adanInterface.getTiming(year,month, params)
    }


}