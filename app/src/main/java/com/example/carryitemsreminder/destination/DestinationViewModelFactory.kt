package com.example.carryitemsreminder.destination

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

class DestinationViewModelFactory (private val application:Application,
                                   private val navigation: NavController) :ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DestinationViewModel::class.java)) {
            return DestinationViewModel(application, navigation) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}