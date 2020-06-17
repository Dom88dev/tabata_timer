package com.dom_project.tabata_timer.viewmodel

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.repository.CircuitWorkoutRepository
import kotlinx.android.synthetic.main.item_workout_in_circuit.view.*

class WorkoutDetailViewModel constructor(private val circuitWorkoutRepository: CircuitWorkoutRepository) : ViewModel() {

    val workout = circuitWorkoutRepository.workoutSelected

    fun refreshGifByLabel(view: View, imageUri: String) {
        if (view is TextView) {
            refreshGif(view.rootView.findViewById<ImageView>(R.id.workout_thumbnail), imageUri)
        }
    }

    fun refreshGif(view: View, imageUri: String) {
        if (imageUri.endsWith("gif")) {
            if (view is ImageView)
                when (view.scaleType) {
                    ImageView.ScaleType.CENTER_CROP -> {
                        Glide.with(view.context).asGif().load(imageUri).thumbnail(.1f).centerCrop().into(view)
                    }
                    ImageView.ScaleType.CENTER_INSIDE -> {
                        Glide.with(view.context).asGif().load(imageUri).thumbnail(.1f).centerInside().into(view)
                    }
                    else -> {
                        Glide.with(view.context).asGif().load(imageUri).thumbnail(.1f).fitCenter().into(view)
                    }
                }
        }
        val label = view.rootView.findViewById<TextView>(R.id.label_play_gif)
        if (label.visibility == VISIBLE) label.visibility = GONE
    }

}