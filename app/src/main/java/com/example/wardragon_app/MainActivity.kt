package com.example.wardragon_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.mapbox.maps.MapView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private var responseData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mapView = findViewById(R.id.mapView)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)



        setupDrawerContent(navView)

        // Remove button click listeners as they're moved to the menu
        // Optional: If you want to open the drawer from the toolbar
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    // only have one destroy function
    // Create on destroy function


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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // add buttons
        return when (item.itemId) {
            R.id.action_check_drone_ids -> {
                checkDroneIds()
                true
            }
            R.id.action_generate_test_ids -> {
                generateTestDroneData()
                true
            }
            R.id.messaging -> {
                val intent = Intent(this, MessagingActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun updateDrones() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.1.33:5000/api/check_drone_ids?type=android")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MainActivity", "API call failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    responseData?.let {
                        val dronesJsonArray = JSONArray(it)
                        for (i in 0 until dronesJsonArray.length()) {
                            val droneJson = dronesJsonArray.getJSONObject(i)
                            val drone = Drone.fromJSON(droneJson)
                            saveDrone(drone)
                        }
                    }
                } else {
                    Log.e("MainActivity", "API call failed: ${response.message}")
                }
            }
        })
    }

    private fun saveDrone(drone: Drone) {
        DroneManager.addDrone(drone)
        Log.d("MainActivity", "Drone saved: $drone")
    }

    private fun deleteDroneObject(droneId: String) {
        DroneManager.removeDroneById(droneId)
        Log.d("MainActivity", "Drone deleted: $droneId")
    }

    private fun checkDroneIds() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.1.33:5000/api/check_drone_ids?type=android")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MainActivity", "API call failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    responseData = response.body?.string()
                    runOnUiThread {
                        // Update UI with the response data (e.g., show a toast or update a TextView)
                        Log.d("MainActivity", "API call successful: $responseData")
                    }
                } else {
                    Log.e("MainActivity", "API call failed: ${response.message}")
                }
            }
        })
    }



    private fun generateTestDroneData() {
        val testDrone1 = Drone(
            id = "test_id_1",
            latitude = 10.0,
            longitude = 20.0,
            horizontalSpeed = 5.0,
            verticalSpeed = 1.0,
            altitude = 100.0,
            height = 50.0,
            description = "Test Drone 1"
        )

        val testDrone2 = Drone(
            id = "test_id_2",
            latitude = 15.0,
            longitude = 25.0,
            horizontalSpeed = 6.0,
            verticalSpeed = 2.0,
            altitude = 150.0,
            height = 60.0,
            description = "Test Drone 2"
        )

        DroneManager.addDrone(testDrone1)
        DroneManager.addDrone(testDrone2)

        Toast.makeText(this, "Test drones generated and saved", Toast.LENGTH_SHORT).show()
        Log.d("MainActivity", "Test drones generated: $testDrone1, $testDrone2")
    }

    // Keep your existing methods like checkDroneIds, generateTestDroneData, etc.




}

