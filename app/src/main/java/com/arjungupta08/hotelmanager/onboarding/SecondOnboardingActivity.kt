package com.arjungupta08.hotelmanager.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arjungupta08.hotelmanager.databinding.ActivitySecondOnboardingBinding
import com.arjungupta08.hotelmanager.utils.fetchCountryName
import com.arjungupta08.hotelmanager.utils.shakeAnimation

class SecondOnboardingActivity : AppCompatActivity() {
    private lateinit var bindingMobile : ActivitySecondOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMobile = ActivitySecondOnboardingBinding.inflate(layoutInflater)
        setContentView(bindingMobile.root)

        // ------------ Display the country name using timezone---------
        val countryName = fetchCountryName()
        if (countryName != "Unknown"){
            bindingMobile.countryText.setText(countryName)
        }

        bindingMobile.cardSingleNext.setOnClickListener {
            if (bindingMobile.propertyText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.propertyLayout,applicationContext)
                bindingMobile.propertyLayout.error = "Please enter property name"
            } else if (bindingMobile.addressText.text!!.isEmpty()) {
                bindingMobile.addressLayout.error = "Please enter property address"
                shakeAnimation(bindingMobile.addressLayout,applicationContext)
                bindingMobile.propertyLayout.isErrorEnabled = false
            } else if (bindingMobile.stateText.text!!.isEmpty()) {
                bindingMobile.stateLayout.error = "Please enter state"
                shakeAnimation(bindingMobile.stateLayout,applicationContext)
                bindingMobile.stateLayout.isErrorEnabled = false
            } else if (bindingMobile.countryText.text!!.isEmpty()) {
                bindingMobile.countryLayout.error = "Please enter currency"
                shakeAnimation(bindingMobile.countryLayout,applicationContext)
            } else {
                sendDataMobile()
            }
        }
    }

    private fun sendDataMobile() {

    }
}