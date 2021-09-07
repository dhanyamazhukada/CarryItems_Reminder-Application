package com.example.carryitemsreminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarryItemsDao {
    @Insert
    suspend fun insert(item: CarryItemEntity)

    @Query("SELECT * from carry_items_table WHERE item_type = :type ORDER BY itemId DESC")
    suspend fun getAllItems(type: String): List<CarryItemEntity>?

    @Query("DELETE FROM carry_items_table WHERE itemId = :key")
    suspend fun deleteItem(key: Long):Int

    @Update
    suspend fun updateItem(item: CarryItemEntity)
}