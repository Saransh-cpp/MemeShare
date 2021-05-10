package com.example.memeshare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val listener: NewsItemClicked) : RecyclerView.Adapter<CategoryViewHolder>() {
    private val item = arrayListOf<String>("memes", "dankmemes")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        val viewHolder = CategoryViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = item[position]
        holder.titleView.text = current
    }

    override fun getItemCount(): Int {
        return item.size
    }
}

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView : TextView = itemView.findViewById(R.id.categoryText)
}

interface NewsItemClicked {
    fun onItemClicked(item: String)
}