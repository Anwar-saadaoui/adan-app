package com.praytimeadan.adan.Components

import android.annotation.SuppressLint
import com.praytimeadan.adan.model.PrayerTimings
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Utils {

    companion object{
        fun getCurrentDayTiming(list: List<PrayerTimings>):PrayerTimings?{
            return list.find { prayerTimings ->
                prayerTimings.date.gregorian.date == getCurrentDate()
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun getCurrentDate(): String {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("dd-MM-yyyy")
            return dateFormat.format(calendar.time)
        }


        fun getStringToDate(): Date {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return sdf.parse(getCurrentDate())!!
        }

        fun getSubTimeExact(time:String):Pair<String, String>{
            val hours = time.substring(0,2)
            val minutes = time.substring(3,5)
            val hour2 = hours.substring(1,2)
            val minutes2 = minutes.substring(1)

            return if (hours.startsWith("0")
                && minutes.startsWith("0")
            ){
                Pair(hour2, minutes2)
            }else if (hours.startsWith("0")
                && !minutes.startsWith("0")){
                Pair(hour2,minutes)
            }else if(!hours.startsWith("0")
                && minutes.startsWith("0")){
                Pair(hours, minutes2)
            }else{
                Pair(hours, minutes)
            }
        }


        fun calculateInitialDelay(hour: Int, minute: Int): Long {
            val currentTime = Calendar.getInstance()
            val targetTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                if (before(currentTime)) {
                    add(Calendar.DATE, 1) // Set the date to tomorrow if the target time has passed today
                }
            }
            return targetTime.timeInMillis - currentTime.timeInMillis
        }



    }


}