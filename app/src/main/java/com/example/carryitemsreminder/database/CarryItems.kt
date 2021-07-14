package com.example.carryitemsreminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carry_items_reminder_table")
data class CarryItems(
    @PrimaryKey(autoGenerate = true)
    var destination: String = "",

    @ColumnInfo(name = "carry_item")
    val carryItem: String = "",

    @ColumnInfo(name = "carry_item_status")
    val carryItemStatus: Boolean = false
)