package com.dom_project.tabata_timer.ui.workout_in_circuit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.ActivityWorkoutInCircuitListBinding
import com.dom_project.tabata_timer.ui.BindingActivity
import com.dom_project.tabata_timer.viewmodel.WorkoutInCircuitViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkoutInCircuitListActivity : BindingActivity<ActivityWorkoutInCircuitListBinding>() {

    val viewModel : WorkoutInCircuitViewModel by viewModel()

    override fun getLayoutResId(): Int = R.layout.activity_workout_in_circuit_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.circuitProgramSelected.observe(this, Observer {
            Log.d("workoutsInCircuit", "detect circuitProgram changed!! : circuit name = ${it.circuit.name} / workout list size = ${it.workouts.size}")
            binding.circuit = it.circuit
            binding.workouts = it.workouts
        })

//        val circuitId = intent.getLongExtra("circuitId", 0)
        viewModel.loadWorkoutListData(viewModel.circuitProgramSelected.value?.circuit!!.id)

        binding.vm = viewModel
        binding.workouts = viewModel.circuitProgramSelected.value?.workouts
        binding.circuit = viewModel.circuitProgramSelected.value?.circuit
        binding.activity = this
        binding.lifecycleOwner = this
        //todo 운동순서 바꾸기 기능..
    }
}
