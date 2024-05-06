package com.praytimeadan.adan.Components

import android.content.Context
import android.media.MediaPlayer
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.praytimeadan.adan.R
import java.util.concurrent.TimeUnit

class Adan {

    companion object {
        fun adin(context: Context) {
            val mediaPlayer = MediaPlayer.create(context, R.raw.adan)
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        fun scheduleAdanTime(context: Context, time: String) {
            val prayerTime = PeriodicWorkRequestBuilder<AdanWorker>(1, TimeUnit.DAYS)
                .setInitialDelay(
                        Utils.calculateInitialDelay(
                        Utils.getSubTimeExact(time).first.toInt(),
                        Utils.getSubTimeExact(time).second.toInt()
                    ),
                    TimeUnit.MILLISECONDS
                )
                .build()
            WorkManager.getInstance(context).enqueue(prayerTime)
        }

    }


}