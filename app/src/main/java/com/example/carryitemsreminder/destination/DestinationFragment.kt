package com.example.carryitemsreminder.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.carryitemsreminder.R
import com.example.carryitemsreminder.databinding.FragmentDestinationBinding

class DestinationFragment : Fragment() {

    private lateinit var binding: FragmentDestinationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_destination, container, false)

        val application = requireNotNull(this.activity).application
        findNavController().let {
            val viewModelFactory = DestinationViewModelFactory(application, it)

            // Get reference to the viewModel using viewModelFactory reference
            val destinationViewModel = ViewModelProvider(this, viewModelFactory).get(DestinationViewModel::class.java)

            // Assign the destinationViewModel binding variable to the destinationViewModel
            binding.destinationViewModel = destinationViewModel

            // Set the current activity as the lifecycle owner of the binding
            binding.setLifecycleOwner (this)
        }

        return binding.root
    }
}