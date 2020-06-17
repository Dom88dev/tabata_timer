package com.dom_project.tabata_timer.ui.binding_adpters.timer

import androidx.databinding.BindingAdapter
import com.dom_project.tabata_timer.R
import com.xenione.digit.TabDigit

@BindingAdapter("maxTens")
fun setTensCharArrayDigit(view: TabDigit, max: Int) {
    when (max) {
        9 -> view.chars = charArrayOf('9', '8', '7', '6', '5', '4', '3', '2', '1', '0')
        8 -> view.chars = charArrayOf('8', '7', '6', '5', '4', '3', '2', '1', '0')
        7 -> view.chars = charArrayOf('7', '6', '5', '4', '3', '2', '1', '0')
        6 -> view.chars = charArrayOf('6', '5', '4', '3', '2', '1', '0')
        5 -> view.chars = charArrayOf('5', '4', '3', '2', '1', '0')
        4 -> view.chars = charArrayOf('4', '3', '2', '1', '0')
        3 -> view.chars = charArrayOf('3', '2', '1', '0')
        2 -> view.chars = charArrayOf('2', '1', '0')
        1 -> view.chars = charArrayOf('1', '0')
        0 -> view.chars = charArrayOf('0')
    }
    view.setChar(0)
}

@BindingAdapter("startUnit")
fun setUnitCharArrayDigit(view: TabDigit, start: Int) {
    view.chars = charArrayOf('0','9', '8', '7', '6', '5', '4', '3', '2', '1')
    when (start) {
        0 -> view.setChar(0)
        else -> view.setChar(10-start)
    }
}

@BindingAdapter("isWorkoutNow")
fun setBackgroundColor(view: TabDigit, isWorkout: Boolean) {
    if (isWorkout) view.backgroundColor = view.context.getColor(android.R.color.black)
    else view.backgroundColor = view.context.getColor(R.color.colorPrimary)
    view.textColor = view.context.getColor(android.R.color.white)
}