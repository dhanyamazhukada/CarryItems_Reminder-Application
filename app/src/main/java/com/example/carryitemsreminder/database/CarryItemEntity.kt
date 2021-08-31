package com.example.carryitemsreminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carry_items_table")
data class CarryItemEntity(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    @ColumnInfo(name = "carry_item")
    val carryItem: String = "",

    @ColumnInfo(name = "carry_item_status")
    var carryItemStatus: Boolean = false
)