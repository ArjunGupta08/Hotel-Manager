package com.arjungupta08.hotelmanager.dashboard

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.arjungupta08.hotelmanager.DashboardActivity
import com.arjungupta08.hotelmanager.databinding.ActivityPropertyProfileBinding
import com.arjungupta08.hotelmanager.utils.UtilityCollections
import com.arjungupta08.hotelmanager.utils.showProgressDialog
import com.bumptech.glide.Glide

class PropertyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPropertyProfileBinding

    private lateinit var progressDialog: Dialog
    private val documentId: String
        get() = intent.getStringExtra("documentId").toString()
    private val hotelName: String
        get() = intent.getStringExtra("hotelName").toString()
    private val imageUrl: String
        get() = intent.getStringExtra("imageUrl").toString()
    private val location: String
        get() = intent.getStringExtra("location").toString()
    private val ratingText: String
        get() = intent.getStringExtra("ratingText").toString()
    private val rooms: String
        get() = intent.getStringExtra("rooms").toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(imageUrl).into(binding.vendorImage)
        binding.hotelName.text = hotelName
        binding.location.text = location
        binding.hotelRating.text = ratingText
        binding.rooms.text = "$rooms Rooms"

        binding.delete.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Are you sure")
        dialog.setPositiveButton("Delete") { dialog, which ->
            progressDialog = showProgressDialog(this)
            deleteHotel()
        }
        dialog.setNegativeButton("Not Now") { dialog, which ->
            dialog.dismiss()
        }
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun deleteHotel() {
        val getProperty =
            UtilityCollections.getCollectionReferenceForProperties().document(documentId)

        getProperty.delete().addOnSuccessListener {
            progressDialog.dismiss()
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
            .addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_SHORT).show()
                Log.d("delete", it.message.toString())
            }
    }
}