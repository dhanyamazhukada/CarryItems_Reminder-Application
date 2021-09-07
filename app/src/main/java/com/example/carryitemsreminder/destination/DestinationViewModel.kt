package com.example.carryitemsreminder.destination

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.RoomDatabase
import com.example.carryitemsreminder.R
import com.example.carryitemsreminder.carryitemslist.CarryItemsFragment
import com.example.carryitemsreminder.database.CarryItemEntity
import com.example.carryitemsreminder.database.CarryItemsDao
import com.example.carryitemsreminder.database.CarryItemsDatabase
import kotlinx.coroutines.launch

class DestinationViewModel(application: Application, private val navigation: NavController)
    : AndroidViewModel(application) {

    private lateinit var navController: NavController

    fun onDestinationSelected(){
        viewModelScope.launch {
            navController = navigation
            navController.navigate(
                R.id.action_destinationFragment_to_carryItemsFragment,
                bundleOf("type" to "Shopping" ))
        }
    }

    fun onGymSelected(){
        viewModelScope.launch {
            navController = navigation
            navController.navigate(
                R.id.action_destinationFragment_to_carryItemsFragment,
                bundleOf("type" to "Gym" )
            )
        }
    }
}