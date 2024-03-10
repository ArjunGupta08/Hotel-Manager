package com.arjungupta08.hotelmanager.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arjungupta08.hotelmanager.R
import com.arjungupta08.hotelmanager.databinding.ActivitySecondOnboardingBinding

class SecondOnboardingActivity : AppCompatActivity() {
    private lateinit var bindingMobile : ActivitySecondOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMobile = ActivitySecondOnboardingBinding.inflate(layoutInflater)
        setContentView(bindingMobile.root)



    }
}