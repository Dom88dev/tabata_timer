package com.dom_project.tabata_timer.ui.binding_adpters.main

import android.content.Intent
import android.os.Handler
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dom_project.tabata_timer.model.local.entity.Circuit
import com.dom_project.tabata_timer.model.local.entity.CircuitProgram
import com.dom_project.tabata_timer.ui.circuit_list.CircuitListActivity
import com.dom_project.tabata_timer.ui.main.MainActivity
import com.dom_project.tabata_timer.viewmodel.MainViewModel

/*
 리스트에 표시할 weather 객체의 List를 리싸이클러뷰의 어댑터에 세팅하고 리스트를 새로고침핸다.
 어댑터가 없으면 WeatherHistoryAdapter를 생성해서 지정해 준다.
 */
@BindingAdapter(value = ["circuits", "viewModel"])
fun setCircuitListItems(view: TextView, circuits: List<CircuitProgram>, vm: MainViewModel) {
    if (circuits.isNotEmpty()) vm.insertCircuitProgram(circuits)
}
