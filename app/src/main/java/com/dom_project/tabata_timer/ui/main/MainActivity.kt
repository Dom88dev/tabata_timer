package com.dom_project.tabata_timer.ui.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.MainActivityBinding
import com.dom_project.tabata_timer.model.local.entity.Circuit
import com.dom_project.tabata_timer.model.local.entity.CircuitProgram
import com.dom_project.tabata_timer.model.local.entity.Workout
import com.dom_project.tabata_timer.ui.BindingActivity
import com.dom_project.tabata_timer.ui.circuit_list.CircuitListActivity
import com.dom_project.tabata_timer.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BindingActivity<MainActivityBinding>() {

    //액티비티 내의 프레그먼트와 하나의 뷰모델을 공유하기 위해 따로 프로퍼티를 선언.
    val mainViewModel: MainViewModel by viewModel()
    var circuits: MutableList<CircuitProgram> = mutableListOf()

    override fun getLayoutResId(): Int = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.data.observe(this, Observer {
            if (it.isNotEmpty()) {
                Log.d("MainActivity", "observed circuitProgram change!! : size = ${it.size} / circuit[0].name = ${it[0].circuit.name} / circuit[0].workouts size = ${it[0].workouts.size}")
                goToCircuitList()
            }
        })

        binding.lifecycleOwner = this
        binding.vm = mainViewModel

        if (mainViewModel.data.value.isNullOrEmpty()) {
            Log.d("MainActivity", "make data from resource file..")
            val circuitNames = resources.getStringArray(R.array.workout_list)
            val ta = resources.obtainTypedArray(R.array.workout_content_list)
            val n = ta.length()
            for (i in 0 until n) {
                val id = ta.getResourceId(i, 0)
                if (id > 0) {
                    val ta2 = resources.obtainTypedArray(id)
                    val n2 = ta2.length()
                    val circuit = Circuit(name = circuitNames[i])
                    val workoutList: MutableList<Workout> = mutableListOf()
                    for (j in 0 until n2) {
                        val id2 = ta2.getResourceId(j, 0)
                        if (id2 > 0) {
                            val workoutContents = resources.getStringArray(id2)
                            if (workoutContents.size == 3) {
                                workoutList.add(Workout(name = workoutContents[0], howTo = workoutContents[1], thumbnail = workoutContents[2]))
                            }
                        }
                    }
                    circuits.add(CircuitProgram(circuit, workoutList))
                    ta2.recycle()
                }
            }
            ta.recycle()
        }
        binding.circuitPrograms = circuits
    }

    private fun goToCircuitList() {
        startActivity(Intent(this, CircuitListActivity::class.java))
        finish()
    }
}
