package com.arjungupta08.hotelmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.arjungupta08.hotelmanager.dashboard.DashboardFragment
import com.arjungupta08.hotelmanager.dashboard.addProperty.AddPropertyFragment
import com.arjungupta08.hotelmanager.databinding.ActivityDashboardActivtyBinding
import com.arjungupta08.hotelmanager.onboarding.ThirdOnboardingActivity
import com.arjungupta08.hotelmanager.onboarding.UserData
import com.arjungupta08.hotelmanager.utils.UtilityCollections
import com.arjungupta08.hotelmanager.utils.bottomSlideInAnimation
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.toObject

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashboardActivtyBinding

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(DashboardFragment())
        toolBar()
        sideNav()

        getUser()

    }

    private fun getUser() {
        val user = UtilityCollections.getCollectionReferenceForUser().get()
        user.addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    try {
                        for (document : QueryDocumentSnapshot in result.result) {
                            val userList = document.toObject(UserData::class.java)
                            Log.d("TAG", "${document.id} => ${document.data}")
                            binding.phoneNumber.text = userList.phone
                            "${userList.firstName} ${userList.lastName}".also { binding.name.text = it }
                            Glide.with(this).load(userList.imageUrl).into(binding.profile)
                        }
                    } catch (e:Exception) {
                        Log.d("e" , ": $e")
                    }
                }

            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }

    }

    private fun toolBar() {

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set the toolbar as the support action bar
        setSupportActionBar(binding.toolbar)

        // Enable the up button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.svg_menu)
        // Set click listener for the up button

        binding.toolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        binding.imgNotify.setOnClickListener {

        }

        binding.quickAdd.setOnClickListener {
            val intent = Intent(this, ThirdOnboardingActivity::class.java)
            intent.putExtra("fromDash", true)
            startActivity(intent)
//            replaceFragment(AddPropertyFragment())
        }
    }
    private fun sideNav() {
        binding.dashboardCard.setOnClickListener {
            isCardSelected(binding.dashboardCard, binding.dashboardTxt)
            binding.quickAdd.isVisible = true
        }
        binding.addPropertyCard.setOnClickListener {
            isCardSelected(binding.addPropertyCard, binding.addPropertyTxt)
//            replaceFragment(AddPropertyFragment())
            val intent = Intent(this, ThirdOnboardingActivity::class.java)
            intent.putExtra("fromDash", true)
            startActivity(intent)
            binding.quickAdd.isVisible = false
        }
        binding.helpCard.setOnClickListener {
            isCardSelected(binding.helpCard, binding.helpTxt)
        }
    }
    private fun isCardSelected(card: MaterialCardView, text: TextView) {

        binding.dashboardCard.setCardBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.white
            )
        )
        binding.dashboardTxt.setTextColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.black
            )
        )

        binding.addPropertyCard.setCardBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.white
            )
        )
        binding.addPropertyTxt.setTextColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.black
            )
        )

        binding.helpCard.setCardBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.white
            )
        )
        binding.helpTxt.setTextColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.black
            )
        )

        card.setCardBackgroundColor(ContextCompat.getColor(applicationContext, R.color.black))
        text.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.dashboardFragmentContainer,fragment)
        transaction.commit()

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

        bottomSlideInAnimation(binding.dashboardFragmentContainer, applicationContext)
    }

}