package com.mehmet.cs388_project5

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var caloriesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        nameTextView = findViewById(R.id.name)
        caloriesTextView = findViewById(R.id.calories)

        // TODO: Get the extra from the Intent
        val nutritionItem = intent.getSerializableExtra(NUTRITION_EXTRA) as DisplayNutrition

        // TODO: Set the title, byline, and abstract information from the article
        nameTextView.text = nutritionItem.name
        caloriesTextView.text = nutritionItem.calories.toString() + " calories"
    }
}