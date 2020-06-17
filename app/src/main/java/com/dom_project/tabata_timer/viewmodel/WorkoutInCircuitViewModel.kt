package com.dom_project.tabata_timer.viewmodel

import android.app.ActivityOptions
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
import com.dom_project.tabata_timer.ui.workout_in_circuit.EditDialog
import com.dom_project.tabata_timer.ui.workout_in_circuit.WorkoutInCircuitListActivity
import kotlinx.android.synthetic.main.item_workout_in_circuit.view.*
import kotlinx.coroutines.launch
// Rename the Pair class from the Android framework to avoid a name clash
import android.util.Pair as UtilPair

class WorkoutInCircuitViewModel constructor(private val circuitWorkoutRepository: CircuitWorkoutRepository) : ViewModel() {

    val circuitProgramSelected: MutableLiveData<CircuitProgram> = circuitWorkoutRepository.getCircuitProgramSelected()

    fun loadWorkoutListData(circuitId: Long) {
        viewModelScope.launch {
            val cp = circuitProgramSelected.value
            cp?.workouts = circuitWorkoutRepository.getWorkoutsInCircuitFormDb(circuitId)
            circuitProgramSelected.value = cp
        }
    }

    fun onClickWorkoutItem(activity: WorkoutInCircuitListActivity, view: View, workout: Workout) {
        Log.d("WorkoutInCircuit", "item Clicked!!")
        val root = view.rootView
        if (root is ViewGroup) {
            Log.d("WorkoutInCircuit", "move to detail")
            circuitWorkoutRepository.workoutSelected.value = workout
            val options = ActivityOptions.makeSceneTransitionAnimation(activity,
                UtilPair.create(root.findViewById(R.id.workout_thumbnail), "workout_thumbnail"),
                UtilPair.create(root.findViewById(R.id.workout_name), "workout_name"))
            activity.startActivity(Intent(activity, DetailWorkoutActivity::class.java), options.toBundle())
        }

    }

    fun onClickFloatingActionPlay(view: View) {
        view.context.startActivity(Intent(view.context, TimerActivity::class.java))
    }

    fun onClickForCallEditDialog(vm: WorkoutInCircuitViewModel, activity: WorkoutInCircuitListActivity, kind: Int) {
        EditDialog(activity, vm, kind).show()
    }

    fun onClickEditCircuitInfo(view: View, dialog: EditDialog, kind: Int) {
        val newValue = view.rootView.findViewById<EditText>(R.id.dialog_input).text.toString()
        val circuitP = circuitProgramSelected.value
        if (circuitP != null) {
            when(kind) {
                0 -> {
                    circuitP.circuit.workoutTime = newValue.toInt()
                }
                1 -> {
                    circuitP.circuit.restTime = newValue.toInt()
                }
                else -> {
                    circuitP.circuit.repeatCount = newValue.toInt()
                }
            }
            viewModelScope.launch {
                Log.d("Edit_Dialog", "save data changing to db")
                circuitWorkoutRepository.updateCircuit(circuitP.circuit)
            }
            circuitProgramSelected.value = circuitP
        }

        dialog.dismiss()
    }
}