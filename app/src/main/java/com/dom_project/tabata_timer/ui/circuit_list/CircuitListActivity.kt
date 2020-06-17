package com.dom_project.tabata_timer.ui.circuit_list

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.ActivityCircuitListBinding
import com.dom_project.tabata_timer.ui.BindingActivity
import com.dom_project.tabata_timer.viewmodel.CircuitListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CircuitListActivity : BindingActivity<ActivityCircuitListBinding>() {

    val viewModel : CircuitListViewModel by viewModel()

    override fun getLayoutResId(): Int  = R.layout.activity_circuit_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.items = viewModel.circuits.value
        binding.activity = this

        viewModel.circuits.observe(this, Observer {
            Log.d("Circuit List", "detect circuit program data changed!!")
            binding.items = it
        })
    }
}