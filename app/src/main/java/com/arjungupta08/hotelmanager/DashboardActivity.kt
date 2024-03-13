package com.arjungupta08.hotelmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.arjungupta08.hotelmanager.databinding.ActivityDashboardActivtyBinding

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

}