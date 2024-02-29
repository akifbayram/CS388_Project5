package com.mehmet.cs388_project5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.cs388_project5.NutritionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NutritionLogFragment : Fragment() {

    private val entries = mutableListOf<NutritionEntity>()
    private lateinit var nutritionDao: NutritionDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nutrition_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nutritionDao = (activity?.application as NutritionApplication).db.nutritionDao()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewNutritionLog)
        setupRecyclerView(recyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val adapter = NutritionAdapter(entries)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        fillList(adapter)
    }

    private fun fillList(adapter: NutritionAdapter) {
        lifecycleScope.launch(Dispatchers.IO) {
            nutritionDao.getAll().collect { list -> // This will collect the Flow
                withContext(Dispatchers.Main) {
                    entries.clear()
                    entries.addAll(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
