package com.example.carryitemsreminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarryItemsDao {
    @Insert
    suspend fun insert(item: CarryItemEntity)

    @Query("SELECT * from carry_items_table ORDER BY itemId DESC")
    fun getAllItems(): List<CarryItemEntity>?

    @Query("DELETE FROM carry_items_table WHERE itemId = :key")
    suspend fun deleteItem(key:Int):CarryItemEntity
}