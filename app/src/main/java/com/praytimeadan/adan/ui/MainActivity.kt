package com.praytimeadan.adan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.praytimeadan.adan.Components.Adan
import com.praytimeadan.adan.database.DBDate
import com.praytimeadan.adan.database.DBTimings
import com.praytimeadan.adan.databinding.ActivityMainBinding
import com.praytimeadan.adan.Components.Utils
import com.praytimeadan.adan.R
import com.praytimeadan.adan.viewModel.AdanViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val currentDate = LocalDate.now()

    private lateinit var adanViewModel: AdanViewModel
    private val location =
        mapOf("latitude" to "32.507056", "longitude" to "-6.685796", "method" to "3")

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adanViewModel = ViewModelProvider(this)[AdanViewModel::class.java]
        adanViewModel.prayerTimings.observe(this) { prayerTimingsResponse ->
            val lisDataTiming = prayerTimingsResponse.data
            val prayerTiming = Utils.getCurrentDayTiming(lisDataTiming)
            prayerTiming?.let {
                //TODO: Here do what you want by the data timing, date, hijri, gregorian
                adanViewModel.init(application)
                // timing
                val fajr = it.timings.Fajr
                Adan.scheduleAdanTime(applicationContext,fajr)
                val sunrise = it.timings.Sunrise
                val dhuhr = it.timings.Dhuhr
                Adan.scheduleAdanTime(applicationContext,dhuhr)
                val asr = it.timings.Asr
                Adan.scheduleAdanTime(applicationContext,asr)
                val maghrib = it.timings.Maghrib
                Adan.scheduleAdanTime(applicationContext,maghrib)
                val isha = it.timings.Isha
                Adan.scheduleAdanTime(applicationContext,isha)
                // dates
                val hijriDates = it.date.hijri.date
                val gregorianDates = it.date.gregorian.date

                val timing = DBTimings(Utils.getCurrentDate(),fajr, sunrise, dhuhr, asr, maghrib,isha)
                val dating = DBDate(gregorianDates, hijriDates)
                adanViewModel.insertTiming(timing)
                adanViewModel.insertDate(dating)

                adanViewModel.getTimingVM().observe(this){ dbTiming ->
                    val fajrV = dbTiming.Fajr
                    val dohrV = dbTiming.Dhuhr
                    val asrV = dbTiming.Asr
                    val maghrebV = dbTiming.Maghrib
                    val ishaV = dbTiming.Isha
                    binding.fajrTv.text = fajrV
                    binding.topFajr.text = fajrV
                    binding.dohrTv2.text = dohrV
                    binding.asrTime.text = asrV
                    binding.maghrebTv.text = maghrebV
                    binding.ishaTime2.text = ishaV
                }

                adanViewModel.getDateVM().observe(this){ dbDate ->
                    val hijri = dbDate.gregorian
                    binding.dateHijriTv.text = hijri
                }

            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                adanViewModel.fetchPrayerTimings(currentDate.year, currentDate.monthValue, location)
            }
        }

//        val animationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
//        animationView.setAnimation("fajr.json")
//        animationView.playAnimation()
//        val lottie = binding.lottieAnimationView
//        lottie.setAnimation("fajr.json")
//        lottie.playAnimation()


    }
}