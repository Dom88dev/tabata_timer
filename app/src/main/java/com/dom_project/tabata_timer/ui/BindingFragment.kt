package com.dom_project.tabata_timer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * 데이터 바인딩뷰를 가진 데이터바인딩 프레그먼트의 슈퍼클래스
 */
abstract class BindingFragment<T : ViewDataBinding> : Fragment() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var binding: T
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<T>(inflater, getLayoutResId(), container, false).apply { binding = this }.root
    }
}