package com.example.carryitemsreminder.destination

import android.app.Application
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.carryitemsreminder.R
import kotlinx.coroutines.launch

class DestinationViewModel(application: Application,
                           private val navigation: NavController) : AndroidViewModel(application) {

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