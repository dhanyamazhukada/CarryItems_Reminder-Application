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
class CarryItemsViewModel(private val binding: FragmentCarryitemsBinding) : ViewModel() {

    private val allItems: MutableList<CarryItemEntity> = mutableListOf()
    //val database = CarryItemsDatabase.getInstance(getApplication()).carryitemsDao

    fun onAddItemClicked(){
        val itemAdapter = CarryItemAdapter(allItems)
        binding.rvItemsList.adapter = itemAdapter

        val enterItem = binding.etEnterItem.text.toString()
        if(enterItem.isNotEmpty()){
            val item = CarryItemEntity(0, enterItem, carryItemStatus = false)
            itemAdapter.addItem(item)
            binding.etEnterItem.text.clear()
        }
    }
    fun onDeleteItemClicked(){
        val itemAdapter = CarryItemAdapter(allItems)
        binding.rvItemsList.adapter = itemAdapter
        itemAdapter.deleteItem()
    }
//    fun getAllItemsObservers(): MutableLiveData<List<CarryItemEntity>> {
//        return allItems
//    }

//    fun getAllItemsInfo() {
//        val list = database.getAllItems()
//        allItems.postValue(list)
//    }
//    fun insertItem(entity: CarryItemEntity){
//        database.insert(entity)
//        getAllItemsInfo()
//    }
}