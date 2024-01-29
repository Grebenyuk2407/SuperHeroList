package com.example.superherolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SuperHeroAdapter : RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {

    private var superHeroList: List<SuperHeroItem> = emptyList()

    fun setData(superHeroList: List<SuperHeroItem>) {
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val slugTextView: TextView = itemView.findViewById(R.id.slugTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSuperHero = superHeroList[position]

        holder.nameTextView.text = "Name: ${currentSuperHero.name}"
        holder.slugTextView.text = "Slug: ${currentSuperHero.slug}"

        Glide.with(holder.itemView.context)
            .load(currentSuperHero.images.sm)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return superHeroList.size
    }
}
