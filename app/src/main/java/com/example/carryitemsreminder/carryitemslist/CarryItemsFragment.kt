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

class CarryItemsFragment : Fragment(), EventListener {
    private var carryItemsViewModel: CarryItemsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        val binding: FragmentCarryitemsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_carryitems, container, false)

        val type = arguments?.getString("type")
        Log.d("TEST", "onCreateView: type: $type")

        binding.rvItemsList.adapter = CarryItemAdapter(mutableListOf(), this)

        val datasource = context?.let { CarryItemsDatabase.getInstance(it).carryitemsDao }

        val viewModelFactory = datasource?.let { CarryItemsViewModelFactory( binding, it, type) }

        // Get reference to the viewModel using viewmodelFactory reference
        carryItemsViewModel = viewModelFactory?.let {
            ViewModelProvider(this, it).get(CarryItemsViewModel::class.java)
        }

        // Assign the destinationViewModel binding variable to the destinationViewModel
        binding.carryitemsViewModel = carryItemsViewModel

        // Set the current activity as the lifecycle owner of the binding
        binding.setLifecycleOwner (this)

        return binding.root
    }

    override fun onCheckedStateChanged(item: CarryItemEntity) {
        carryItemsViewModel?.updateItem(item)
    }

}