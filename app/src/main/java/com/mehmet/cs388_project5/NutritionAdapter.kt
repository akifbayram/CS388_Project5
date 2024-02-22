package com.mehmet.cs388_project5

import android.content.Context
import android.content.Intent
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val NUTRITION_EXTRA = "NUTRITION_EXTRA"
private const val TAG = "NutritionAdapter"

class NutritionAdapter(private val context: Context, private val nutritionList: List<DisplayNutrition>) :
    RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.nutrition_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nutritionItem = nutritionList[position]
        holder.bind(nutritionItem)
    }

    override fun getItemCount() = nutritionList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
            private val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
            private val caloriesTextView = itemView.findViewById<TextView>(R.id.caloriesTextView)


        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(nutritionItem: DisplayNutrition) {
            nameTextView.text = nutritionItem.name
            caloriesTextView.text = nutritionItem.calories.toString() + " calories"
        }

        override fun onClick(v: View?) {
            // TODO: Get selected item
            val nutritionItem = nutritionList[absoluteAdapterPosition]


            // TODO: Navigate to Details screen and pass selected nutrition item
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(NUTRITION_EXTRA, nutritionItem)
            context.startActivity(intent)

        }

    }
}