package com.dom_project.tabata_timer.ui.circuit_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dom_project.tabata_timer.R
import com.dom_project.tabata_timer.databinding.ItemCircuitBinding
import com.dom_project.tabata_timer.model.local.entity.CircuitProgram
import com.dom_project.tabata_timer.ui.BindingViewHolder
import com.dom_project.tabata_timer.viewmodel.CircuitListViewModel

class CircuitListViewAdapter(val activity: CircuitListActivity, val vm: CircuitListViewModel) : RecyclerView.Adapter<CircuitListViewAdapter.CircuitListViewHolder>() {

    private var items: MutableList<CircuitProgram> = if (vm.circuits.value.isNullOrEmpty()) mutableListOf() else vm.circuits.value!!.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircuitListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_circuit, parent, false)
        Log.d("CircuitList", "onCreateViewHolder : start value size = ${items.size}")
        return CircuitListViewHolder(view)
    }

    override fun getItemCount(): Int = if (items.isNullOrEmpty()) 0 else items.size

    override fun onBindViewHolder(holder: CircuitListViewHolder, position: Int) {
        holder.binding.vm = vm
        holder.binding.item = items[position].circuit
        holder.binding.workoutCount = items[position].workouts.size
        holder.binding.activity = activity
    }

    fun setValues(data: List<CircuitProgram>) {
        items = data.toMutableList()
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): CircuitProgram {
        return items[position]
    }


    inner class CircuitListViewHolder(view: View) : BindingViewHolder<ItemCircuitBinding>(view)
}