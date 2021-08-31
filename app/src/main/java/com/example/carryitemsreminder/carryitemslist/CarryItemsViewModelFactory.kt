package com.example.carryitemsreminder.carryitemslist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carryitemsreminder.database.CarryItemsDao


//class CarryItemsViewModelFactory (private val destination:Long, private val dataSource : CarryItemsDao)
class CarryItemsViewModelFactory ()
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarryItemsViewModel::class.java)) {
            return CarryItemsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}