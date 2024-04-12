package com.arjungupta08.hotelmanager.onboarding

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.arjungupta08.hotelmanager.BuildConfig
import com.arjungupta08.hotelmanager.DashboardActivity
import com.arjungupta08.hotelmanager.R
import com.arjungupta08.hotelmanager.databinding.ActivityThirdOnboardingBinding
import com.arjungupta08.hotelmanager.utils.fetchCountryName
import com.arjungupta08.hotelmanager.utils.rightToLeftEditImageAnimation
import com.arjungupta08.hotelmanager.utils.setMargins
import com.arjungupta08.hotelmanager.utils.shakeAnimation
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import java.lang.IndexOutOfBoundsException

class ThirdOnboardingActivity : AppCompatActivity() {
    private lateinit var bindingMobile : ActivityThirdOnboardingBinding

    private var roomCount = 1

    private var imageUri: Uri ?= null

    private var isImageSelected = false
    private var isImageEdit = false

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        try {
            imageUri = it
            isImageSelected = true
            setMargins(bindingMobile.galleryImg, 0, 0, 0, 0)
            bindingMobile.galleryImg.setImageURI(imageUri)
        } catch (e: RuntimeException) {
            Log.d("cropperOnPersonal", e.toString())
        } catch (e: ClassCastException) {
            Log.d("cropperOnPersonal", e.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMobile = ActivityThirdOnboardingBinding.inflate(layoutInflater)
        setContentView(bindingMobile.root)


        // Select Image From Gallery
        imageSelectionMobile()

        // Google Places api for addresses
        placesAPIMobile()

        // ------------ Display the country name using timezone---------
        val countryName = fetchCountryName()
        if (countryName != "Unknown"){
            bindingMobile.countryText.setText(countryName)
        }


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

        bindingMobile.cardSkip.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        bindingMobile.cardSingleNext.setOnClickListener {
            if (bindingMobile.propertyText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.propertyLayout, applicationContext)
                bindingMobile.propertyLayout.error = "Please enter tax name"
            } else if (bindingMobile.addressText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.addressLayout,applicationContext)
                bindingMobile.addressLayout.error = "Please enter property address"
                bindingMobile.propertyLayout.isErrorEnabled = false
            } else if (bindingMobile.stateText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.stateLayout,applicationContext)
                bindingMobile.stateLayout.error = "Please enter state"
                bindingMobile.addressLayout.isErrorEnabled = false
                bindingMobile.propertyLayout.isErrorEnabled = false
            } else if (bindingMobile.countryText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.countryLayout,applicationContext)
                bindingMobile.countryLayout.error = "Please enter currency"
                bindingMobile.addressLayout.isErrorEnabled = false
                bindingMobile.stateLayout.isErrorEnabled = false
                bindingMobile.propertyLayout.isErrorEnabled = false
            } else {
                bindingMobile.addressLayout.isErrorEnabled = false
                bindingMobile.stateLayout.isErrorEnabled = false
                bindingMobile.propertyLayout.isErrorEnabled = false
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

    private fun imageSelectionMobile() {
        bindingMobile.replaceImage.setOnClickListener {
            openGallery()
            rightToLeftEditImageAnimation(bindingMobile.imageEditLayout, applicationContext)
            isImageEdit = false
            bindingMobile.imageEditLayout.isVisible = false
        }
        bindingMobile.removeImage.setOnClickListener {
            imageUri = Uri.EMPTY
            bindingMobile.galleryImg.setImageResource(R.drawable.svg_gallery)
            rightToLeftEditImageAnimation(bindingMobile.imageEditLayout, applicationContext)
            isImageEdit = false
            isImageSelected = false
            bindingMobile.imageEditLayout.isVisible = false
            setMargins(bindingMobile.galleryImg, 15, 15, 15, 15)
        }
        bindingMobile.cardGallery.setOnClickListener {
            if (!isImageSelected){
                openGallery()
            } else {
                if (!isImageEdit) {
                    bindingMobile.imageEditLayout.isVisible = true
                    // load the animation
                    val anim: Animation = AnimationUtils.loadAnimation(
                        applicationContext,
                        R.anim.l_to_r_in_animation
                    )
                    // start the animation
                    bindingMobile.imageEditLayout.startAnimation(anim)
                    isImageEdit = true
                } else {
                    rightToLeftEditImageAnimation(bindingMobile.imageEditLayout, applicationContext)
                    isImageEdit = false
                    bindingMobile.imageEditLayout.isVisible = false
                }
            }
        }
    }

    private fun openGallery() {
        contract.launch("image/*")
    }

    private fun placesAPIMobile() {
        val apiKey = BuildConfig.GOOGLE_PLACES_API_KEY
        // Initialize Places API
        Places.initialize(applicationContext, apiKey)

        // Create a PlacesClient
        val placesClient: PlacesClient = Places.createClient(this)

        // Initialize the AutoCompleteTextView and adapter
        val autoCompleteTextView = bindingMobile.addressText
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line)
        autoCompleteTextView.setAdapter(adapter)

        // Set up the Autocomplete request
        autoCompleteTextView.threshold = 1  // Minimum characters to start autocomplete
        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val selectedAddress = adapter.getItem(position).toString()

            // Handle the selected address as needed
            val lastThreeWords = extractLastThreeWords(selectedAddress)
            println("Last Three Words: $lastThreeWords")
            try {
                autoCompleteTextView.setText(removeLastThreeWords(selectedAddress))
//                bindingMobile!!.cityText.setText(lastThreeWords.get(0))
                bindingMobile.stateText.setText(lastThreeWords[1])
                bindingMobile.countryText.setText(lastThreeWords[2])
            } catch (e : IndexOutOfBoundsException){
                println(e.toString())
            }
        }

        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Create a FindAutocompletePredictionsRequest
                val request = FindAutocompletePredictionsRequest.builder()
                    .setSessionToken(AutocompleteSessionToken.newInstance())
                    .setQuery(s.toString())
                    .build()

                // Perform the autocomplete request
                placesClient.findAutocompletePredictions(request).addOnSuccessListener { response ->
                    val predictions = response.autocompletePredictions
                    val suggestionList = mutableListOf<String>()

                    for (prediction in predictions) {
                        suggestionList.add(prediction.getFullText(null).toString())
                        Log.d("suggestion", suggestionList.toString())
                    }

                    // Update the adapter with the new suggestions
                    adapter.clear()
                    adapter.addAll(suggestionList)
                    autoCompleteTextView.setAdapter(adapter)
                    adapter.notifyDataSetChanged()
                }.addOnFailureListener { exception ->
                    println(exception.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
    private fun extractLastThreeWords(input: String): List<String> {
        val words = input.split(",").map { it.trim() }
        return if (words.size >= 3) {
            words.subList(words.size - 3, words.size)
        } else {
            words
        }
    }
    private fun removeLastThreeWords(input: String): String {
        val words = input.split(",").map { it.trim() }
        return if (words.size >= 3) {
            words.subList(0, words.size - 3).joinToString(", ")
        } else {
            ""
        }
    }

}