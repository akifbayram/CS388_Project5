package com.codepath.cs388_project5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.mehmet.cs388_project5.NutritionEntity

@Dao
interface NutritionDao {
    @Query("SELECT * FROM nutrition_table")
    fun getAll(): Flow<List<NutritionEntity>>

    @Insert
    fun insert(nutritionEntity: NutritionEntity)

    @Query("DELETE FROM nutrition_table")
    fun deleteAll()

    @Delete
    fun delete(nutrition: NutritionEntity)
}
