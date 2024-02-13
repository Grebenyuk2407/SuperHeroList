package com.example.superherolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class DetailFragment : Fragment() {
    private lateinit var superHeroItem : SuperHeroItem
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_superhero_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.detailImageView)
        val eyeColorTextView: TextView = view.findViewById(R.id.eyeColorTextView)
        val genderTextView: TextView = view.findViewById(R.id.genderTextView)
        val placeOfBirthTextView: TextView = view.findViewById(R.id.placeOfBirthTextView)
        val publisherTextView: TextView = view.findViewById(R.id.publisherTextView)
        val firstAppearanceTextView: TextView = view.findViewById(R.id.firstAppearanceTextView)
        val alterEgosTextView: TextView = view.findViewById(R.id.alterEgosTextView)
        val hairColorTextView: TextView = view.findViewById(R.id.hairColorTextView)
        view.findViewById<Button>(R.id.closed_btn).setOnClickListener {

            fragmentManager?.popBackStack()

        }



        Glide.with(requireContext())
            .load(superHeroItem.images.lg)
            .into(imageView)

        eyeColorTextView.text = "Eye Color: ${superHeroItem.appearance.eyeColor}"
        genderTextView.text = "Gender: ${superHeroItem.appearance.gender}"
        hairColorTextView.text = "Hair Color: ${superHeroItem.appearance.hairColor}"
        placeOfBirthTextView.text = "Place of Birth: ${superHeroItem.biography.placeOfBirth}"
        publisherTextView.text = "Publisher: ${superHeroItem.biography.publisher}"
        firstAppearanceTextView.text = "First Appearance: ${superHeroItem.biography.firstAppearance}"
        alterEgosTextView.text = "Alter Egos: ${superHeroItem.biography.alterEgos}"
    }

    fun setSuperHero(superHeroItem: SuperHeroItem) {
        this.superHeroItem = superHeroItem
    }



}