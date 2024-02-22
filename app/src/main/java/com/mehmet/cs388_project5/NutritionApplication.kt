package com.mehmet.cs388_project5;

import android.app.Application;
import com.codepath.cs388_project5.AppDatabase

class NutritionApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}
