package com.praytimeadan.adan.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praytimeadan.adan.database.DBDate
import com.praytimeadan.adan.database.DBTimings
import com.praytimeadan.adan.database.DbDateDao
import com.praytimeadan.adan.database.DbTimingDoa
import com.praytimeadan.adan.database.OfflineRoom
import com.praytimeadan.adan.model.PrayerTimingsResponse
import com.praytimeadan.adan.retrofit.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdanViewModel : ViewModel() {
    private val _prayerTimings = MutableLiveData<PrayerTimingsResponse>()
    val prayerTimings:MutableLiveData<PrayerTimingsResponse>
        get() = _prayerTimings
    lateinit var dbTimingsInterface: DbTimingDoa
    lateinit var dbDateInterface: DbDateDao
    lateinit var dbTimingsData: LiveData<DBTimings>
    lateinit var dbDateData: LiveData<DBDate>



    suspend fun fetchPrayerTimings(year: Int, month: Int, params: Map<String, String>) {
        val retrofitBuilder:PrayerTimingsResponse = RetrofitBuilder.getINSTANCE().getTamingData(year, month, params)
        _prayerTimings.apply {
            value = retrofitBuilder
        }
    }

    fun init(application: Application) {
        val database = OfflineRoom.getDatabase(application)

        dbTimingsInterface = database.dbTiming()
        dbTimingsData = dbTimingsInterface.getTiming()

        dbDateInterface = database.dbDate()
        dbDateData = dbDateInterface.getDates()
    }

    fun getTimingVM(): LiveData<DBTimings> {
        return dbTimingsData
    }

    fun getDateVM():LiveData<DBDate>{
        return dbDateData
    }

    fun insertTiming(dbTimings: DBTimings) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dbTimingsInterface.insertDbTiming(dbTimings)
            }
        }
    }

    fun insertDate(dbDate: DBDate) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dbDateInterface.insertDbDate(dbDate)
            }
        }
    }

}
