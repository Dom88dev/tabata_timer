package com.dom_project.tabata_timer.ui.binding_adpters.circuit_list

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dom_project.tabata_timer.model.local.entity.CircuitProgram
import com.dom_project.tabata_timer.ui.circuit_list.CircuitListActivity
import com.dom_project.tabata_timer.ui.circuit_list.CircuitListViewAdapter
import com.dom_project.tabata_timer.viewmodel.CircuitListViewModel

@BindingAdapter(value = ["data", "viewModel", "activity"], requireAll = true)
fun setCircuitProgramListItems(view: RecyclerView, items: List<CircuitProgram>, vm: CircuitListViewModel, activity: CircuitListActivity) {
    Log.d("CircuitList", "items size = ${items.size}")
    view.adapter?.run {
        if (this is CircuitListViewAdapter) {
            if (!items.isNullOrEmpty()) this.setValues(items)
        }
    } ?: run {
        Log.d("CircuitList", "make adapter!")
        CircuitListViewAdapter(activity, vm).apply { view.adapter = this }
        Log.d("CircuitList", "adapter item count = ${view.adapter?.itemCount}")
    }
}


@BindingAdapter("viewModel")
fun setItemTouchHelper(view: RecyclerView, vm: CircuitListViewModel) {
    ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPos = viewHolder.adapterPosition
            val toPos = target.adapterPosition
            if (fromPos != toPos) {
                view.adapter?.run {
                    if (this is CircuitListViewAdapter) {

                    }
                }
                return true
            }
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            vm.onSwipeItemInCircuitList(viewHolder, direction, view)
        }
    }).attachToRecyclerView(view)
}
