package com.dom_project.tabata_timer.viewmodel

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.model.local.entity.CircuitProgram
import com.dom_project.tabata_timer.model.local.entity.Workout
import com.dom_project.tabata_timer.repository.CircuitWorkoutRepository
import com.dom_project.tabata_timer.ui.detail_workout.DetailWorkoutActivity
import com.dom_project.tabata_timer.ui.timer.TimerActivity
import com.dom_project.tabata_timer.ui.timer.TimerCompleteDialog
import com.dom_project.tabata_timer.ui.workout_in_circuit.WorkoutInCircuitListActivity
import kotlinx.android.synthetic.main.item_workout_in_circuit.view.*
import kotlinx.coroutines.launch
// Rename the Pair class from the Android framework to avoid a name clash
import android.util.Pair as UtilPair

class TimerViewModel constructor(private val circuitWorkoutRepository: CircuitWorkoutRepository) : ViewModel() {

    val circuitProgramSelected: MutableLiveData<CircuitProgram> = circuitWorkoutRepository.getCircuitProgramSelected()

    fun loadWorkoutListData(circuitId: Long) {
        viewModelScope.launch {
            val cp = circuitProgramSelected.value
            cp?.workouts = circuitWorkoutRepository.getWorkoutsInCircuitFormDb(circuitId)
            circuitProgramSelected.value = cp
        }
    }


    fun onClickPause() {

    }

    fun onClickPrevious() {

    }

    fun onClickNext() {

    }

    fun onClickStop() {

    }

    fun onClickComplete(dialog: TimerCompleteDialog, activity: Activity) {
        dialog.dismiss()
        activity.finish()
    }
}