package com.example.superherolist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SuperHeroAdapter : RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {

    private var superHeroes: ListSuperHero = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(superHeroes: ListSuperHero) {
        this.superHeroes = superHeroes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val superHero = superHeroes[position]


        Picasso.get().load(superHero.images.xs).into(holder.imageView)

        holder.nameTextView.text = superHero.name
        holder.slugTextView.text = superHero.slug
    }

    override fun getItemCount(): Int {
        return superHeroes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val slugTextView: TextView = itemView.findViewById(R.id.slugTextView)
    }
}
