package com.example.superherolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(val items:MutableList<SuperHero>):RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val listItemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)

        return RecyclerViewHolder(listItemView)

    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.name.text = items[position].name
        holder.slug.text = items[position].slug

        Glide.with(holder.itemView.context)
            .load(items[position].images.xs)
            .into(holder.image)

    }
}


class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

val image:ImageView = itemView.findViewById(R.id.list_image)

val name: TextView = itemView.findViewById(R.id.text_name)

val slug:TextView = itemView.findViewById(R.id.text_slug)

}