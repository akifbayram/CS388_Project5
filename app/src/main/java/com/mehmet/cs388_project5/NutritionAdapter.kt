package com.mehmet.cs388_project5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NutritionAdapter(private val nutritionList: MutableList<NutritionEntity>) : RecyclerView.Adapter<NutritionAdapter.NutritionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder {
        return NutritionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NutritionViewHolder, position: Int) {
        holder.bind(nutritionList[position])
    }

    override fun getItemCount(): Int = nutritionList.size

    class NutritionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodNameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val caloriesTextView: TextView = itemView.findViewById(R.id.caloriesTextView)

        fun bind(nutritionEntity: NutritionEntity) {
            foodNameTextView.text = nutritionEntity.name
            caloriesTextView.text = "${nutritionEntity.calories} calories"
        }

        companion object {
            fun create(parent: ViewGroup): NutritionViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.nutrition_item, parent, false)
                return NutritionViewHolder(view)
            }
        }
    }
}