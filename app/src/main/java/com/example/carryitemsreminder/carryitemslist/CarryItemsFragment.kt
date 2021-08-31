package com.example.carryitemsreminder.carryitemslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carryitemsreminder.R
import com.example.carryitemsreminder.database.CarryItemEntity
import com.example.carryitemsreminder.database.CarryItemsDatabase
import com.example.carryitemsreminder.databinding.FragmentCarryitemsBinding
import kotlinx.android.synthetic.main.fragment_carryitems.*

class CarryItemsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {

        val binding: FragmentCarryitemsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_carryitems, container, false)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = CarryItemsViewModelFactory()

        // Get reference to the viewModel using viewmodelFactory reference
        val carryItemsViewModel = ViewModelProvider(
            this, viewModelFactory).get(CarryItemsViewModel::class.java)

        // Assign the destinationViewModel binding variable to the destinationViewModel
        binding.carryitemsViewModel = carryItemsViewModel

        // Set the current activity as the lifecycle owner of the binding
        binding.setLifecycleOwner (this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shoppingCarryItems: MutableList<CarryItemEntity> = mutableListOf()
        val itemAdapter = CarryItemAdapter(shoppingCarryItems)

        rvItemsList.adapter = itemAdapter
        rvItemsList.layoutManager = LinearLayoutManager(this.context)


        btnAddItem.setOnClickListener {
            val enterItem = etEnterItem.text.toString()
            if(enterItem.isNotEmpty()){
                val item = CarryItemEntity(0, enterItem, carryItemStatus = false) // added to db
                itemAdapter.addItem(item)
                etEnterItem.text.clear()
            }
        }

        btnDeleteItem.setOnClickListener {
            itemAdapter.deleteItem()
        }

    }
}