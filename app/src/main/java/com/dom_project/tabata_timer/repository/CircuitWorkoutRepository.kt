package com.dom_project.tabata_timer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dom_project.tabata_timer.model.local.dao.CircuitProgramDao
import com.dom_project.tabata_timer.model.local.entity.Circuit
import com.dom_project.tabata_timer.model.local.entity.CircuitProgram
import com.dom_project.tabata_timer.model.local.entity.CircuitWorkoutJoin
import com.dom_project.tabata_timer.model.local.entity.Workout

class CircuitWorkoutRepository(private val circuitProgramDao: CircuitProgramDao) {
    private val circuitPrograms: MutableLiveData<List<CircuitProgram>> = MutableLiveData(listOf())

    private var circuitProgramSelected: MutableLiveData<CircuitProgram> = MutableLiveData()
    var workoutSelected: MutableLiveData<Workout> = MutableLiveData()

    suspend fun getDataFromDb() : List<CircuitProgram> {
        circuitPrograms.value = circuitProgramDao.getCircuitPrograms()
        return circuitPrograms.value!!
    }

    fun setCircuitProgramSelected(circuit: Circuit) {

        for (cp in circuitPrograms.value!!) {
            Log.d("Repository", "selected circuit id = ${circuit.id} / circuit id in circuitProgram = ${cp.circuit.id}")
            if (circuit.id == cp.circuit.id) {
                Log.d("Repository", "setting selected circuitProgram")
                circuitProgramSelected.value = cp
                break
            }
        }
    }

    fun getCircuitProgramSelected() : MutableLiveData<CircuitProgram> {
        return circuitProgramSelected
    }


    suspend fun insertDefaultCircuitProgramDataToDb(data: List<CircuitProgram>) {
        var cobraId = 0L
        data.forEachIndexed { i, program ->
            val id = circuitProgramDao.insertCircuit(program.circuit)
            Log.d("DAO", "insert : inserted circuit id = $id")
            val idList = circuitProgramDao.insertWorkoutList(program.workouts)
            Log.d("DAO", "insert : inserted workout id size = ${idList.size} / workout size = ${program.workouts.size}")
            program.workouts.forEachIndexed { index, workout ->
                if (workout.name == "코브라 자세") {
                    if (i == 0) {
                        cobraId = idList[index]
                        circuitProgramDao.insertCircuitWorkoutJoin(CircuitWorkoutJoin(id, idList[index], index))
                        Log.d("DAO", "insert : inserted circuit_id = $id / workout_id = ${idList[index]} / order = $index")
                    } else {
                        circuitProgramDao.insertCircuitWorkoutJoin(CircuitWorkoutJoin(id, cobraId, index))
                        Log.d("DAO", "insert : inserted circuit_id = $id / workout_id = $cobraId / order = $index")
                    }
                } else {
                    circuitProgramDao.insertCircuitWorkoutJoin(CircuitWorkoutJoin(id, idList[index], index))
                    Log.d("DAO", "insert : inserted circuit_id = $id / workout_id = ${idList[index]} / order = $index")
                }
            }
        }
    }

    suspend fun updateCircuit(data: Circuit) {
        circuitProgramDao.updateCircuit(data)
    }

    suspend fun insertCircuitDataToDb(data: Circuit) {
        circuitProgramDao.insertCircuits(data)
    }

    suspend fun insertWorkoutDataToDb(data:List<Workout>) {
        circuitProgramDao.insertWorkoutList(data)
    }

    suspend fun getCircuitFormDbById(id: Long) : Circuit {
        return circuitProgramDao.getCircuitById(id)
    }

    suspend fun getCircuitFromDbByName(name: String): Circuit {
        return circuitProgramDao.getCircuitByName(name)
    }

    suspend fun getWorkoutsFromDb() : List<Workout> {
        return circuitProgramDao.getWorkouts()
    }

    suspend fun getWorkoutsInCircuitFormDb(id: Long) : List<Workout> {
        return circuitProgramDao.getWorkoutListInCircuit(id)
    }

    fun setCircuitPrograms(circuitPrograms: List<CircuitProgram>) {
        this.circuitPrograms.value = circuitPrograms
    }

    fun getCircuitPrograms() : MutableLiveData<List<CircuitProgram>> {
        return circuitPrograms
    }

}