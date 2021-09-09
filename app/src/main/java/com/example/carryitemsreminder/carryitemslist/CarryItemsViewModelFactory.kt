package com.example.carryitemsreminder.carryitemslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carryitemsreminder.database.CarryItemsDao
import com.example.carryitemsreminder.databinding.FragmentCarryitemsBinding


class CarryItemsViewModelFactory (private val binding: FragmentCarryitemsBinding,
                                  private val database: CarryItemsDao,
                                  private val itemType: String?)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarryItemsViewModel::class.java)) {
            return itemType?.let { CarryItemsViewModel( binding, database, it) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}