package com.example.carryitemsreminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarryItemsDao {

    @Insert
    suspend fun insert(itemsList: CarryItems)

    @Update
    suspend fun update(itemsList: CarryItems)

//    @Query("SELECT * from carry_items_reminder_table WHERE destination = :key")
//    suspend fun get(key: Long): SleepNight?
//
//    @Query("DELETE FROM carry_items_reminder_table")
//    suspend fun clear()
//
//    @Query("SELECT * FROM carry_items_reminder_table ORDER BY destination DESC LIMIT 1")
//    suspend fun getTonight(): SleepNight?
//
//    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
//    fun getAllNights(): LiveData<List<SleepNight>>
}