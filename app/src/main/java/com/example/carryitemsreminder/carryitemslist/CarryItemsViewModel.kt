package com.example.carryitemsreminder.carryitemslist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carryitemsreminder.database.CarryItemEntity
import com.example.carryitemsreminder.database.CarryItemsDao
import com.example.carryitemsreminder.database.CarryItemsDatabase
import kotlinx.coroutines.launch

//class CarryItemsViewModel(private val destination:Long = 0L,val database: CarryItemsDao)
class CarryItemsViewModel()
    : ViewModel() {

    var allItems : MutableLiveData<List<CarryItemEntity>> = MutableLiveData()

    init{
        getAllItemsInfo()
    }

    fun getAllItemsObservers(): MutableLiveData<List<CarryItemEntity>> {
        return allItems
    }

    fun getAllItemsInfo() {
    }
}