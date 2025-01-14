package com.example.wardragon_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MessagingActivity : AppCompatActivity() {

    private lateinit var messagingNavView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messaging) // Assume you have a layout for this activity

        // setup toolbar to be visible in messaging screen
        val messageToolBar: Toolbar = findViewById(R.id.message_toolbar)
        setSupportActionBar(messageToolBar)


        messagingNavView = findViewById(R.id.messaging_view)

        setupDrawerContent(messagingNavView)


        // Remove button click listeners as they're moved to the menu
        // Optional: If you want to open the drawer from the toolbar
        messageToolBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Set up your UI here for adding contacts, creating messages, etc.
        setupUI()
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                // You might want to keep these if they're still needed for the drawer
//                R.id.nav_check_drone -> checkDroneIds()
//                R.id.nav_generate_test -> generateTestDroneData()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setupUI() {
        // Here you would set up your UI elements like buttons or list views for messaging features
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.messaging_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_contact -> {
                // Logic to add a contact
                true
            }
            R.id.action_new_message -> {
                // Logic to create a new message
                true
            }
            R.id.action_view_messages -> {
                // Logic to view messages
                true
            }
            // Add in move to home to button
            else -> super.onOptionsItemSelected(item)
        }
    }
}