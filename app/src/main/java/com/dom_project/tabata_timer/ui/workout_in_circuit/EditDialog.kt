package com.dom_project.tabata_timer.ui.workout_in_circuit

import android.content.Context
import android.os.Bundle
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.DialogEditBinding
import com.dom_project.tabata_timer.ui.BindingDialog
import com.dom_project.tabata_timer.viewmodel.WorkoutInCircuitViewModel

class EditDialog(context: Context, val vm: WorkoutInCircuitViewModel, val kinds: Int): BindingDialog<DialogEditBinding>(context) {

    override fun getLayoutResId(): Int = R.layout.dialog_edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
        binding.kind = kinds
        binding.circuit = vm.circuitProgramSelected.value?.circuit
        binding.dialog = this
    }
}