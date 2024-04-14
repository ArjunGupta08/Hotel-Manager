package com.arjungupta08.hotelmanager.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arjungupta08.hotelmanager.PropertyAdapter
import com.arjungupta08.hotelmanager.PropertyDataClass
import com.arjungupta08.hotelmanager.R
import com.arjungupta08.hotelmanager.databinding.FragmentDashboardBinding
import com.arjungupta08.hotelmanager.onboarding.UserData
import com.arjungupta08.hotelmanager.utils.UtilityCollections
import com.bumptech.glide.Glide
import com.google.firebase.firestore.QueryDocumentSnapshot

class DashboardFragment : Fragment() {
    private lateinit var binding : FragmentDashboardBinding

    private lateinit var propertyAdapter: PropertyAdapter

    val propertyList = arrayListOf<PropertyDataClass>()
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

        getProperty()

        binding.propertyRecyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun getProperty() {
        val user = UtilityCollections.getCollectionReferenceForProperties().get()
        user.addOnCompleteListener { result ->
            if (result.isSuccessful) {
                try {
                    for (document : QueryDocumentSnapshot in result.result) {
                        val property = document.toObject(PropertyDataClass::class.java)
                        Log.d("TAG", "${document.id} => ${document.data}")
                        propertyList.add(property)
                    }
                    propertyAdapter = PropertyAdapter(requireContext(), propertyList)
                    binding.propertyRecyclerView.adapter = propertyAdapter
                } catch (e:Exception) {
                    Log.d("e" , ": $e")
                }
            }
        }.addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }
    }

}