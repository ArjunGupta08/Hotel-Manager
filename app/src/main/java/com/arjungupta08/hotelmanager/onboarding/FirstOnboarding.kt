package com.arjungupta08.hotelmanager.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.arjungupta08.hotelmanager.R
import com.arjungupta08.hotelmanager.databinding.ActivityFirstOnboardingBinding

class FirstOnboarding : AppCompatActivity() {
    private lateinit var bindingMobile : ActivityFirstOnboardingBinding

    private var isSingleSelected = false
    private var isChainSelected = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMobile = ActivityFirstOnboardingBinding.inflate(layoutInflater)
        setContentView(bindingMobile.root)

        bindingMobile.cardSingle.setOnClickListener {

            isSingleSelected = true
            isChainSelected = false


            val colorResId = R.color.grey10 // Replace with your color resource ID
            val color = ContextCompat.getColor(applicationContext, colorResId)
            bindingMobile.cardChain.strokeColor = color

            val colorResId1 = R.color.black // Replace with your color resource ID
            val color1 = ContextCompat.getColor(applicationContext, colorResId1)

            bindingMobile.cardSingle.strokeColor = color1
        }

        bindingMobile.cardChain.setOnClickListener {

            isChainSelected = true
            isSingleSelected = false

            val colorResId = R.color.grey10 // Replace with your color resource ID
            val color = ContextCompat.getColor(applicationContext, colorResId)
            bindingMobile.cardSingle.strokeColor = color
//
            val colorResId1 = R.color.black // Replace with your color resource ID
            val color1 = ContextCompat.getColor(applicationContext, colorResId1)
            bindingMobile.cardChain.strokeColor = color1
        }

        bindingMobile.cardSingleNext.setOnClickListener {
            if (isSingleSelected) {
//                val intent = Intent(this, SecondOnboardingScreen::class.java)
//                startActivity(intent)
            } else if (isChainSelected){
//                val intent = Intent(this, SecondChainOnboardingActivity::class.java)
//                startActivity(intent)
            } else{
                Toast.makeText(this, "Please Select Property Type", Toast.LENGTH_SHORT).show()
            }
        }
    }
}