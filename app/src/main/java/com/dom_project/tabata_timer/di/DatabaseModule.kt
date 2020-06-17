package com.dom_project.tabata_timer.di

import com.dom_project.tabata_timer.model.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


/**
 *
 */
val roomModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single(createdAtStart = false) { get<AppDatabase>().circuitProgramDao() }
}