package com.dom_project.tabata_timer.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingDialog<T : ViewDataBinding>(context: Context) : Dialog(context) {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutResId(), null, false)
        setContentView(binding.root)
    }
}