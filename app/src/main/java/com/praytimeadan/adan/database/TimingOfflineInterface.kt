package com.praytimeadan.adan.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DbTimingDoa {
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    fun insertDbTiming(dbTimings: DBTimings)
    @Query("select * from DBTimings")
    fun getTiming():LiveData<DBTimings>
}

@Dao
interface DbDateDao{
    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    fun insertDbDate(dbDate: DBDate)

    @Query("select * from DBDate")
    fun getDates():LiveData<DBDate>


}