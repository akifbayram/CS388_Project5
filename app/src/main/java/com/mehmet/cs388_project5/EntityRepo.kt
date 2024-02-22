package com.mehmet.cs388_project5

import com.codepath.cs388_project5.NutritionDao

class EntityRepo(private val NutritionDao: NutritionDao) {
    val allNutrition = NutritionDao.getAll()

    suspend fun insert(nutrition: NutritionEntity) {
        NutritionDao.insert(nutrition)
    }
}