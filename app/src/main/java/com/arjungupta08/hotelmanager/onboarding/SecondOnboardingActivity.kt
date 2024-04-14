package com.arjungupta08.hotelmanager.onboarding

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.arjungupta08.hotelmanager.R
import com.arjungupta08.hotelmanager.databinding.ActivitySecondOnboardingBinding
import com.arjungupta08.hotelmanager.utils.UtilityCollections
import com.arjungupta08.hotelmanager.utils.rightToLeftEditImageAnimation
import com.arjungupta08.hotelmanager.utils.setMargins
import com.arjungupta08.hotelmanager.utils.shakeAnimation
import com.arjungupta08.hotelmanager.utils.showProgressDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID


class SecondOnboardingActivity : AppCompatActivity() {
    private lateinit var bindingMobile : ActivitySecondOnboardingBinding

    private var downloadUrl = ""
    private var imageUri: Uri ?= null

    private var isImageSelected = false
    private var isImageEdit = false

    // instance for firebase storage and StorageReference
    private var storageReference: StorageReference? = null
    private var firebaseDatabase : FirebaseDatabase ?= null
    private var storage: FirebaseStorage? = null

    private lateinit var progressDialog : Dialog

    private val email : String
        get() = intent.getStringExtra("email").toString()
    private val password : String
        get() = intent.getStringExtra("password").toString()


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
        bindingMobile = ActivitySecondOnboardingBinding.inflate(layoutInflater)
        setContentView(bindingMobile.root)

        // Select Image From Gallery
        imageSelectionMobile()

        firebaseDatabase = FirebaseDatabase.getInstance()

        // image Upload
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        bindingMobile.cardSingleNext.setOnClickListener {
            if (bindingMobile.firstNameText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.firstNameLayout, applicationContext)
                bindingMobile.firstNameLayout.error = ("Please enter your First Name")
            } else if (bindingMobile.lastNameText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.lastNameLayout, applicationContext)
                bindingMobile.firstNameLayout.isErrorEnabled = false
                bindingMobile.lastNameLayout.error = ("Please enter your Last Name")
            } else if (bindingMobile.phoneText.text!!.isEmpty() || bindingMobile.phoneText.text.toString().length < 10) {
                shakeAnimation(bindingMobile.phoneLayout, applicationContext)
                bindingMobile.firstNameLayout.isErrorEnabled = false
                bindingMobile.lastNameLayout.isErrorEnabled = false
                bindingMobile.phoneLayout.error = ("Please enter your Phone number")
            } else {
                progressDialog = showProgressDialog(this@SecondOnboardingActivity)
                signUpMobile(email, password)
            }
        }
    }

    // Register User
    private fun signUpMobile(email : String, password : String) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                uploadImage()
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Log.e("SignUp", it.message.toString())
                Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }

    // Upload User Data in the order ==> Image --> User Data
    private fun uploadImage() {
        progressDialog.show()

        val filePath = storageReference?.child("images/"+ UUID.randomUUID().toString())

        filePath?.putFile(imageUri!!)?.addOnCompleteListener {
            filePath.downloadUrl.addOnSuccessListener {
                progressDialog.dismiss()
                downloadUrl = it.toString()
                Log.d("url", downloadUrl)
                sendDataMobile()
            }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Log.d("imageNotUploaded", it.message.toString())
                }
        }
            ?.addOnFailureListener{
                progressDialog.dismiss()
                Log.d("urlF", it.message.toString())
            }
    }
    private fun sendDataMobile() {
        progressDialog.show()
        val userData = UserData(downloadUrl,bindingMobile.firstNameText.text.toString(), bindingMobile.lastNameText.text.toString(), bindingMobile.phoneText.text.toString(), bindingMobile.websiteText.text.toString())

        val documentReference = UtilityCollections.getCollectionReferenceForUser().document()
        documentReference.set(userData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    progressDialog.dismiss()
                    val intent = Intent(this, ThirdOnboardingActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    progressDialog.dismiss()
                    Log.d("completedElse", it.exception.toString())
                }
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Log.d("completedElse", it.message.toString())
                Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
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
}