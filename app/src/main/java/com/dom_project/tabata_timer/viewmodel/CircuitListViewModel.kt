package com.dom_project.tabata_timer.viewmodel

import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.dom_project.tabata_timer.model.local.entity.Circuit
import com.dom_project.tabata_timer.repository.CircuitWorkoutRepository
import com.dom_project.tabata_timer.ui.circuit_list.CircuitListActivity
import com.dom_project.tabata_timer.ui.workout_in_circuit.WorkoutInCircuitListActivity

class CircuitListViewModel constructor(private val circuitWorkoutRepository: CircuitWorkoutRepository) : ViewModel() {

    val circuits = circuitWorkoutRepository.getCircuitPrograms()

    fun onSwipeItemInCircuitList(viewHolder: RecyclerView.ViewHolder, direction: Int, view: RecyclerView) {
        //todo do something when swipe item in recyclerview
    }

    fun onClickCircuitName(view: View, circuit:Circuit, activity: CircuitListActivity) {
        circuitWorkoutRepository.setCircuitProgramSelected(circuit)
        val intent = Intent(activity, WorkoutInCircuitListActivity::class.java)
//        intent.putExtra("circuitId", circuit.id)
        view.context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
    }
}