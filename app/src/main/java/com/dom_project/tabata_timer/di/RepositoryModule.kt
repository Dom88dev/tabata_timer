package com.dom_project.tabata_timer.di

import com.dom_project.tabata_timer.repository.CircuitWorkoutRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CircuitWorkoutRepository(get()) }
}