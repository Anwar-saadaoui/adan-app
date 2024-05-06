package com.praytimeadan.adan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DBTimings::class, DBDate::class], version = 1)
abstract class OfflineRoom : RoomDatabase(){

    abstract fun dbTiming():DbTimingDoa
    abstract fun dbDate():DbDateDao

    companion object {
        @Volatile
        private var INSTANCE: OfflineRoom? = null

        fun getDatabase(context: Context): OfflineRoom {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OfflineRoom::class.java,
                    "offline_room"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}