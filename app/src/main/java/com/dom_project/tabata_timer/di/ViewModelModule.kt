package com.dom_project.tabata_timer.di

import com.dom_project.tabata_timer.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { CircuitListViewModel(get()) }
    viewModel { WorkoutInCircuitViewModel(get()) }
    viewModel { WorkoutDetailViewModel(get()) }
    viewModel { TimerViewModel(get()) }
}