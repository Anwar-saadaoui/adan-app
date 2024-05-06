package com.praytimeadan.adan.Components

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.praytimeadan.adan.R

class AdanWorker(context: Context, workerParams: WorkerParameters, ) : Worker(context, workerParams) {
    override fun doWork(): Result {
        // work here:
        Adan.adin(applicationContext)
        Notification(applicationContext, "Time To Pray", "", R.drawable.notifications,"Notification from adan app")
        return Result.success()
    }
}