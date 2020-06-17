package com.dom_project.tabata_timer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dom_project.tabata_timer.model.local.entity.CircuitProgram
import com.dom_project.tabata_timer.repository.CircuitWorkoutRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel constructor(private val circuitWorkoutRepository: CircuitWorkoutRepository) : ViewModel() {
    val data: MutableLiveData<List<CircuitProgram>> = MutableLiveData(listOf())

    fun insertCircuitProgram(data: List<CircuitProgram>) {
        viewModelScope.launch {
            var result = async {
                circuitWorkoutRepository.getDataFromDb()
            }.await()
            if (result.isNullOrEmpty()) {
                Log.d("MainViewModel", "insert data to Db for default values")
                circuitWorkoutRepository.insertDefaultCircuitProgramDataToDb(data)
                result = async {
                    circuitWorkoutRepository.getDataFromDb()
                }.await()
            }
            this@MainViewModel.data.postValue(result)
        }
    }


}
