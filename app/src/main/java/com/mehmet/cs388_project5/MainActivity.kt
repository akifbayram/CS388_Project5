package com.mehmet.cs388_project5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var addButton: Button
    private lateinit var navView: BottomNavigationView

    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK && result.data?.getBooleanExtra("food_recorded", false) == true) {
            navView.selectedItemId = R.id.nav_dashboard
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nutritionLogFragment = NutritionLogFragment()
        val dashboardFragment = DashboardFragment()

        addButton = findViewById(R.id.buttonAdd)
        navView = findViewById(R.id.bottom_navigation)

        navView.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.nav_log -> nutritionLogFragment
                R.id.nav_dashboard -> dashboardFragment
                else -> nutritionLogFragment
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_frame_layout, selectedFragment).commit()
            true
        }

        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        navView.selectedItemId = R.id.nav_log
    }
}
