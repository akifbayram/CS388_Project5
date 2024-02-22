package com.mehmet.cs388_project5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.cs388_project5.AppDatabase
import com.mehmet.cs388_project5.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val nutritionList = mutableListOf<DisplayNutrition>()
    private lateinit var adapter: NutritionAdapter
    private lateinit var nutritionRecyclerView: RecyclerView
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)

        nutritionRecyclerView = findViewById(R.id.nutritionRecyclerView)

        val nutritionAdapter = NutritionAdapter(this, nutritionList)
        nutritionRecyclerView.adapter = nutritionAdapter

        nutritionRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(nutritionRecyclerView.context, it.orientation)
            nutritionRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, 1)
        }

        lifecycleScope.launch {
            (application as NutritionApplication).db.nutritionDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayNutrition(
                        entity.name,
                        entity.calories,
                    )
                }.also { mappedList ->
                    nutritionList.clear()
                    nutritionList.addAll(mappedList)
                    nutritionAdapter.notifyDataSetChanged()
                }

            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val food = NutritionEntity(0, data?.getStringExtra(AddActivity.FOODX).toString(), data?.getStringExtra(AddActivity.CALORIESX).toString().toInt())

                lifecycleScope.launch(Dispatchers.IO) {
                    (application as NutritionApplication).db.nutritionDao().insert(food)
                }

            }
        } else {
            Toast.makeText(
                applicationContext,
                "NOT SAVED",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
