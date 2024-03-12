package com.arjungupta08.hotelmanager.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.arjungupta08.hotelmanager.DashboardActivity
import com.arjungupta08.hotelmanager.databinding.ActivityThirdOnboardingBinding
import com.arjungupta08.hotelmanager.utils.shakeAnimation

class ThirdOnboardingActivity : AppCompatActivity() {
    private lateinit var bindingMobile : ActivityThirdOnboardingBinding

    private var roomCount = 1
    private var rateCount = 1.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMobile = ActivityThirdOnboardingBinding.inflate(layoutInflater)
        setContentView(bindingMobile.root)

        bindingMobile.roomCount.doAfterTextChanged {
            if (bindingMobile.roomCount.text.toString() != "") {
                val roomCountET = bindingMobile.roomCount.text.toString()
                roomCount = roomCountET.toInt()
            } else {
//                Toast.makeText(applicationContext, "This field can not be empty", Toast.LENGTH_SHORT).show()
            }
        }

        bindingMobile.removeRooms.setOnClickListener {
            if (roomCount > 1){
                roomCount--
                bindingMobile.roomCount.setText("$roomCount")
            }
        }
        bindingMobile.addRooms.setOnClickListener {
            roomCount++
            bindingMobile.roomCount.setText("$roomCount")
        }

        bindingMobile.rateCount.doAfterTextChanged {
            if (bindingMobile.rateCount.text.toString() != "") {
                val rateCountET = bindingMobile.rateCount.text.toString()
                rateCount = rateCountET.toDouble()
            } else {
//                Toast.makeText(applicationContext, "This field can not be empty", Toast.LENGTH_SHORT).show()
            }
        }
        bindingMobile.removeRate.setOnClickListener {
            if (rateCount > 1){
                rateCount--
                bindingMobile.rateCount.setText("$rateCount")
            }
        }
        bindingMobile.addRate.setOnClickListener {
            rateCount++
            bindingMobile.rateCount.setText("$rateCount")
        }

        bindingMobile.cardSingleNext.setOnClickListener {
            if (bindingMobile.propertyText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.propertyLayout, applicationContext)
                bindingMobile.propertyLayout.error = "Please enter tax name"
                bindingMobile.TaxNameLayout.isErrorEnabled = false
            } else if (bindingMobile.TaxNameText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.TaxNameText,applicationContext)
                bindingMobile.TaxNameLayout.error = "Please enter registration number"
                bindingMobile.propertyLayout.isErrorEnabled = false
            }
            else {
                sendDataMobile()
            }
        }
    }

    private fun sendDataMobile() {
        val starCategory = bindingMobile.ratingBar.rating.toString()
        val roomsInProperty = bindingMobile.roomCount.text.toString()
        val intent = (Intent(this, DashboardActivity::class.java))
//                        val options = ActivityOptions.makeSceneTransitionAnimation(this,
//                        android.util.Pair(bindingMobile!!.logo,"logo_img"),
//                        android.util.Pair(bindingMobile!!.onBoardingImg,"onBoardingImg")
//                        startActivity(intent, options)
        startActivity(intent)
        finish()
    }

}