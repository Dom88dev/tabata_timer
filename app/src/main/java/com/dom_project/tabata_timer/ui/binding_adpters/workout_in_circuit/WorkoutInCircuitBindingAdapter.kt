package com.dom_project.tabata_timer.ui.binding_adpters.workout_in_circuit

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dom_project.tabata_timer.model.local.entity.Workout
import com.dom_project.tabata_timer.ui.workout_in_circuit.WorkoutInCircuitListActivity
import com.dom_project.tabata_timer.ui.workout_in_circuit.WorkoutsInCircuitViewAdapter
import com.dom_project.tabata_timer.viewmodel.WorkoutInCircuitViewModel

@BindingAdapter(value = ["data", "viewModel", "activity"], requireAll = true)
fun setWorkoutListInCircuitItems(view: RecyclerView, items: List<Workout>, vm: WorkoutInCircuitViewModel, activity: WorkoutInCircuitListActivity) {
    Log.d("WorkoutsInCircuit", "items size = ${items.size}")
    view.adapter?.run {
        if (this is WorkoutsInCircuitViewAdapter) {
            if (!items.isNullOrEmpty()) this.setValues(items)
        }
    } ?: run {
        Log.d("WorkoutsInCircuit", "make adapter!")
        WorkoutsInCircuitViewAdapter(activity, vm).apply { view.adapter = this }
        Log.d("WorkoutsInCircuit", "adapter item count = ${view.adapter?.itemCount}")
    }
}