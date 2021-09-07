package com.example.carryitemsreminder.destination

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.carryitemsreminder.MainActivity
import com.example.carryitemsreminder.R
import com.example.carryitemsreminder.carryitemslist.CarryItemsFragment
import com.example.carryitemsreminder.carryitemslist.CarryItemsViewModel
import com.example.carryitemsreminder.carryitemslist.CarryItemsViewModelFactory
import com.example.carryitemsreminder.database.CarryItemsDatabase
import com.example.carryitemsreminder.databinding.FragmentCarryitemsBinding
import com.example.carryitemsreminder.databinding.FragmentDestinationBinding
import kotlinx.android.synthetic.main.fragment_destination.*
import kotlin.math.log

class DestinationFragment : Fragment() {

    private lateinit var binding: FragmentDestinationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_destination, container, false)

        val application = requireNotNull(this.activity).application
        findNavController().let {
            val viewModelFactory = DestinationViewModelFactory(application, it)

            // Get reference to the viewModel using viewmodelFactory reference
            val destinationViewModel = ViewModelProvider(this, viewModelFactory).get(DestinationViewModel::class.java)

            // Assign the destinationViewModel binding variable to the destinationViewModel
            binding.destinationViewModel = destinationViewModel

            // Set the current activity as the lifecycle owner of the binding
            binding.setLifecycleOwner (this)
        }

        return binding.root
    }
}