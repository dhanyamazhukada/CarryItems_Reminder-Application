package com.example.carryitemsreminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CarryItems::class], version = 1, exportSchema = false)
abstract class CarryItemsDatabase : RoomDatabase() {

    abstract val carryitemsdao: CarryItemsDao

    companion object {

        @Volatile
        private var INSTANCE: CarryItemsDatabase? = null

        fun getInstance(context: Context): CarryItemsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CarryItemsDatabase::class.java,
                        "carry_items_history_database"
                    ).createFromAsset("database/CarryItemsReminder.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}