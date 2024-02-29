package com.mehmet.cs388_project5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codepath.cs388_project5.NutritionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    private lateinit var nutritionDao: NutritionDao
    private lateinit var btnRecord: Button
    private lateinit var nutritionEditText: EditText
    private lateinit var caloriesEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        initializeViews()
        setupButtonListener()
    }

    private fun initializeViews() {
        nutritionDao = (application as NutritionApplication).db.nutritionDao()
        btnRecord = findViewById(R.id.buttonAddFoodRecord)
        nutritionEditText = findViewById(R.id.editAddFood)
        caloriesEditText = findViewById(R.id.editAddCalories)
    }

    private fun setupButtonListener() {
        btnRecord.setOnClickListener {
            validateAndInsertFood()
        }
    }

    private fun validateAndInsertFood() {
        if (nutritionEditText.text.isEmpty() || caloriesEditText.text.isEmpty()) {
            Toast.makeText(this, "Please enter values for Food and Calories", Toast.LENGTH_SHORT).show()
        } else {
            val food = NutritionEntity(
                name = nutritionEditText.text.toString(),
                calories = caloriesEditText.text.toString().toInt()
            )
            insertFood(food)
        }
    }

    private fun insertFood(nutritionEntity: NutritionEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            nutritionDao.insert(nutritionEntity)
        }
        finishActivityWithResult()
    }

    private fun finishActivityWithResult() {
        setResult(RESULT_OK, Intent().apply {
            putExtra("food_recorded", true)
        })
        finish()
    }
}
