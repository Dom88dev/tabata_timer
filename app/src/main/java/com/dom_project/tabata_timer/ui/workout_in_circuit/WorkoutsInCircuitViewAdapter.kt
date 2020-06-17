package com.dom_project.tabata_timer.ui.workout_in_circuit

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.ItemWorkoutInCircuitBinding
import com.dom_project.tabata_timer.model.local.entity.Workout
import com.dom_project.tabata_timer.ui.BindingViewHolder
import com.dom_project.tabata_timer.viewmodel.WorkoutInCircuitViewModel

class WorkoutsInCircuitViewAdapter(val activity: WorkoutInCircuitListActivity, val vm: WorkoutInCircuitViewModel) : RecyclerView.Adapter<WorkoutsInCircuitViewAdapter.WorkoutInCircuitViewHolder>() {

    private var items: MutableList<Workout> = if (vm.circuitProgramSelected.value?.workouts.isNullOrEmpty()) mutableListOf() else vm.circuitProgramSelected.value!!.workouts.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutInCircuitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout_in_circuit, parent, false)
        Log.d("WorkoutsInCircuit", "onCreateViewHolder : start value size = ${items.size}")
        return WorkoutInCircuitViewHolder(view)
    }

    override fun getItemCount(): Int = if (items.isNullOrEmpty()) 0 else items.size

    override fun onBindViewHolder(holder: WorkoutInCircuitViewHolder, position: Int) {
        holder.binding.workout = items[position]
        holder.binding.vm = vm
        holder.binding.activity = activity
    }

    fun setValues(data: List<Workout>) {
        items = data.toMutableList()
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): Workout {
        return items[position]
    }


    inner class WorkoutInCircuitViewHolder(view: View) : BindingViewHolder<ItemWorkoutInCircuitBinding>(view)
}