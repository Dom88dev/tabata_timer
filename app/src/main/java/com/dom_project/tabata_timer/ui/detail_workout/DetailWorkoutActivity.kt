package com.dom_project.tabata_timer.ui.detail_workout

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.ActivityDetailWorkoutBinding
import com.dom_project.tabata_timer.ui.BindingActivity
import com.dom_project.tabata_timer.viewmodel.WorkoutDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailWorkoutActivity : BindingActivity<ActivityDetailWorkoutBinding>() {

    private val viewModel : WorkoutDetailViewModel by viewModel()

    override fun getLayoutResId(): Int = R.layout.activity_detail_workout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.workout.observe(this, Observer {
            Log.d("Workout_Detail", "detect workout value changed!!")
            binding.workout = it
            binding.isGif = it.thumbnail.endsWith("gif")
        })

        binding.vm = viewModel
        binding.workout = viewModel.workout.value
        binding.isGif = viewModel.workout.value?.thumbnail!!.endsWith("gif")
        binding.lifecycleOwner = this

    }
}
