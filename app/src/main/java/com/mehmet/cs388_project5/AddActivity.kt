package com.mehmet.cs388_project5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val button = findViewById<Button>(R.id.buttonAddFoodRecord)
        val foodEditText = findViewById<EditText>(R.id.editAddFood)
        val caloriesEditText = findViewById<EditText>(R.id.editAddCalories)

        button.setOnClickListener {
            val replyIntent = Intent().apply {
                if (TextUtils.isEmpty(foodEditText.text)) {
                    setResult(Activity.RESULT_CANCELED, this)
                } else {
                    putExtra(FOODX, foodEditText.text.toString())
                    putExtra(CALORIESX, caloriesEditText.text.toString())
                    setResult(Activity.RESULT_OK, this)
                }
            }
            finish()
        }
    }

    companion object {
        const val FOODX = "FOODX"
        const val CALORIESX = "CALORIESX"
    }
}