package com.mehmet.cs388_project5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.codepath.cs388_project5.NutritionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment() {

    private var totalCalories = 0
    private var averageCalories = 0
    private var minimumCalories = Int.MAX_VALUE
    private var maximumCalories = 0
    private lateinit var nutritionDao: NutritionDao
    private lateinit var tvAvgCalories: TextView
    private lateinit var tvMinCalories: TextView
    private lateinit var tvMaxCalories: TextView

    companion object {
        @JvmStatic
        fun newInstance(): DashboardFragment = DashboardFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false).also { view ->
            setupUI(view)
        }
    }

    private fun setupUI(view: View) {
        tvAvgCalories = view.findViewById(R.id.averageCaloriesTextView)
        tvMinCalories = view.findViewById(R.id.minCaloriesTextView)
        tvMaxCalories = view.findViewById(R.id.maxCaloriesTextView)
        nutritionDao = (activity?.application as NutritionApplication).db.nutritionDao()
        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch(Dispatchers.IO) {
            processNutritionData()
        }
    }

    private suspend fun processNutritionData() {
        nutritionDao.getAll().collect { nutritionList ->
            nutritionList.forEach { nutritionEntity ->
                val calories = nutritionEntity.calories?.toInt() ?: 0
                totalCalories += calories

                if (calories < minimumCalories) minimumCalories = calories
                if (calories > maximumCalories) maximumCalories = calories
            }

            averageCalories = if (nutritionList.isNotEmpty()) totalCalories / nutritionList.size else 0

            updateUI()
        }
    }

    private suspend fun updateUI() = withContext(Dispatchers.Main) {
        tvAvgCalories.text = averageCalories.toString()
        tvMinCalories.text = minimumCalories.toString()
        tvMaxCalories.text = maximumCalories.toString()
    }
}
