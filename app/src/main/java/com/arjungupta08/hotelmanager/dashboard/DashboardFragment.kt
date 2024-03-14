package com.arjungupta08.hotelmanager.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arjungupta08.hotelmanager.PropertyAdapter
import com.arjungupta08.hotelmanager.R
import com.arjungupta08.hotelmanager.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding : FragmentDashboardBinding

    private lateinit var propertyAdapter: PropertyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.propertyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        propertyAdapter = PropertyAdapter(requireContext(), listOf())
        binding.propertyRecyclerView.adapter = propertyAdapter

    }
}