package com.example.superherolist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {
    private lateinit var superHeroItem : SuperHeroItem
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_superhero_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.detailImageView)
        val eyeColorTextView: TextView = view.findViewById(R.id.eyeColorTextView)
        val genderTextView: TextView = view.findViewById(R.id.genderTextView)
        val hairColorTextView: TextView = view.findViewById(R.id.hairColorTextView)

        Log.d("DetailFragment", "SuperHeroItem: $superHeroItem")


        Glide.with(requireContext())
            .load(superHeroItem.images.lg)
            .into(imageView)

        eyeColorTextView.text = "Eye Color: ${superHeroItem.appearance.eyeColor}"
        genderTextView.text = "Gender: ${superHeroItem.appearance.gender}"
        hairColorTextView.text = "Hair Color: ${superHeroItem.appearance.hairColor}"
    }

    fun setSuperHero(superHeroItem: SuperHeroItem) {
        this.superHeroItem = superHeroItem
    }



}