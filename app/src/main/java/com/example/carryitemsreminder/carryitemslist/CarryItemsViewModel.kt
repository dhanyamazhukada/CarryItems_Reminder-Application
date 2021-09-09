package com.example.carryitemsreminder.carryitemslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carryitemsreminder.database.CarryItemEntity
import com.example.carryitemsreminder.database.CarryItemsDao
import com.example.carryitemsreminder.databinding.FragmentCarryitemsBinding
import kotlinx.coroutines.launch

class CarryItemsViewModel(private val binding: FragmentCarryitemsBinding,
                          private val database: CarryItemsDao,
                          private val itemType: String)
    : ViewModel() {

    init {
        getAllItemsInfo()
    }

    private val allItems: MutableList<CarryItemEntity> = mutableListOf()

    fun onAddItemClicked(){
        val enterItem = binding.etEnterItem.text.toString()
        if(enterItem.isNotEmpty()){
            val item = CarryItemEntity(itemType = itemType, carryItem = enterItem, carryItemStatus = false)
            (binding.rvItemsList.adapter as CarryItemAdapter).addItem(item)
            insertItem(item)
            binding.etEnterItem.text.clear()
        }
    }
    fun onDeleteItemClicked(){
        deleteItem()
        (binding.rvItemsList.adapter as CarryItemAdapter).deleteItem()
    }

    private fun getAllItemsInfo() {
        viewModelScope.launch {
            val list = database.getAllItems(itemType)
            if (list != null) {
                allItems.addAll(list)
            }
            (binding.rvItemsList.adapter as CarryItemAdapter).setItems(allItems)
        }
    }
    private fun insertItem(entity: CarryItemEntity){
        viewModelScope.launch {
            database.insert(entity)
        }
    }

    private fun deleteItem() {
        val itemsToDelete = (binding.rvItemsList.adapter as CarryItemAdapter).getAllCheckedItems()
        viewModelScope.launch {
            for (item in itemsToDelete) {
                database.deleteItem(item.itemId)
            }
        }
    }

    fun updateItem(item: CarryItemEntity) {
        viewModelScope.launch {
            database.updateItem(item)
        }
    }
}