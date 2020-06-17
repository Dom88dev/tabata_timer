package com.dom_project.tabata_timer.ui.timer

import android.app.Activity
import android.os.Bundle
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.DialogCompleteBinding
import com.dom_project.tabata_timer.ui.BindingDialog
import com.dom_project.tabata_timer.viewmodel.TimerViewModel

class TimerCompleteDialog(val activityContext: Activity, val vm: TimerViewModel) : BindingDialog<DialogCompleteBinding>(activityContext) {

    override fun getLayoutResId(): Int = R.layout.dialog_complete

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
        binding.activity = activityContext
        binding.dialog = this
    }
}