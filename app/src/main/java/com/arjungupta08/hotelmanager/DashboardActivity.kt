package com.arjungupta08.hotelmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.arjungupta08.hotelmanager.databinding.ActivityDashboardActivtyBinding
import com.arjungupta08.hotelmanager.utils.bottomSlideInAnimation
import com.google.android.material.card.MaterialCardView

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashboardActivtyBinding

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolBar()

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

        binding.quickReservation.setOnClickListener {

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
                R.color.darkBackground
            )
        )

        card.setCardBackgroundColor(ContextCompat.getColor(applicationContext, R.color.black))
        text.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.dashboardFragmentContainer,fragment)
        transaction.commit()

        bottomSlideInAnimation(binding.dashboardFragmentContainer, applicationContext)

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

}