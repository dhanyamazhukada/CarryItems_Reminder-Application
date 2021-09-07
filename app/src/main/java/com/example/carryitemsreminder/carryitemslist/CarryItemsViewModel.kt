package com.example.carryitemsreminder.carryitemslist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carryitemsreminder.database.CarryItemEntity
import com.example.carryitemsreminder.database.CarryItemsDao
import com.example.carryitemsreminder.database.CarryItemsDatabase
import com.example.carryitemsreminder.databinding.FragmentCarryitemsBinding
import kotlinx.android.synthetic.main.fragment_carryitems.*
import kotlinx.coroutines.launch

//class CarryItemsViewModel(private val destination:Long = 0L,val database: CarryItemsDao)
class CarryItemsViewModel(private val binding: FragmentCarryitemsBinding, private val database: CarryItemsDao,
                          private val itemType: String) : ViewModel() {

    init {
        getAllItemsInfo()
    }

    private val allItems: MutableList<CarryItemEntity> = mutableListOf()
    //val database = CarryItemsDatabase.getInstance(getApplication()).carryitemsDao

    fun onAddItemClicked(){
        //val itemAdapter = CarryItemAdapter(allItems)
        //binding.rvItemsList.adapter = itemAdapter

        val enterItem = binding.etEnterItem.text.toString()
        if(enterItem.isNotEmpty()){
            val item = CarryItemEntity(itemType = itemType, carryItem = enterItem, carryItemStatus = false)
            (binding.rvItemsList.adapter as CarryItemAdapter).addItem(item)
            insertItem(item)
            binding.etEnterItem.text.clear()
        }
    }
    fun onDeleteItemClicked(){
        //val itemAdapter = CarryItemAdapter(allItems)
        //binding.rvItemsList.adapter = itemAdapter
        deleteItem()
        (binding.rvItemsList.adapter as CarryItemAdapter).deleteItem()
    }
//    fun getAllItemsObservers(): MutableLiveData<List<CarryItemEntity>> {
//        return allItems
//    }

    fun getAllItemsInfo() {
        viewModelScope.launch {
            val list = database.getAllItems(itemType)
            if (list != null) {
                allItems.addAll(list)
            }
            //val itemAdapter = CarryItemAdapter(allItems)
            //binding.rvItemsList.adapter = itemAdapter
            (binding.rvItemsList.adapter as CarryItemAdapter).setItems(allItems)
        }
    }
    fun insertItem(entity: CarryItemEntity){
        viewModelScope.launch {
            database.insert(entity)
        }
    }

    fun deleteItem() {
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