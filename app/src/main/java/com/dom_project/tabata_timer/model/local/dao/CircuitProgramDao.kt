package com.dom_project.tabata_timer.model.local.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.dom_project.tabata_timer.model.local.entity.Circuit
import com.dom_project.tabata_timer.model.local.entity.CircuitProgram
import com.dom_project.tabata_timer.model.local.entity.CircuitWorkoutJoin
import com.dom_project.tabata_timer.model.local.entity.Workout

@Dao
interface CircuitProgramDao {

    @Query("SELECT * FROM CIRCUITS")
    suspend fun getCircuitPrograms(): List<CircuitProgram>

    @Query("SELECT * FROM workouts")
    suspend fun getWorkouts(): List<Workout>

    @Query("select * from workouts inner join circuit_workout_join on workouts.id = workout_id where circuit_id = :circuitId order by `order` asc")
    suspend fun getWorkoutListInCircuit(circuitId: Long): List<Workout>

    @Query("select * from circuits where id = :id")
    suspend fun getCircuitById(id: Long): Circuit

    @Query("select * from circuits where name = :name")
    suspend fun getCircuitByName(name: String): Circuit

    /*====================== INSERT ================================*/

    @Insert
    suspend fun insertCircuit(circuit: Circuit): Long

    @Insert
    suspend fun insertCircuits(vararg circuit: Circuit)

    @Insert
    suspend fun insertCircuitList(circuitList: List<Circuit>)

    @Insert
    suspend fun insertWorkOut(workout: Workout): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWorkoutList(workoutList: List<Workout>): List<Long>

    @Insert
    suspend fun insertCircuitWorkoutJoin(cwj: CircuitWorkoutJoin)

    /*====================== UPDATE ================================*/

    @Query("UPDATE circuit_workout_join SET `order` = :order WHERE circuit_id = :circuitId AND workout_id = :workoutId")
    suspend fun updateOrder(order: Int, circuitId: Long, workoutId: Long)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCircuit(vararg circuit: Circuit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkout(vararg workout: Workout)

    @Delete
    suspend fun deleteCircuit(vararg circuit: Circuit)

    @Delete
    suspend fun deleteCircuitList(circuitList: List<Circuit>)

    @Delete
    suspend fun deleteWorkout(vararg workout: Workout)

    @Delete
    suspend fun deleteWorkoutList(workoutList: List<Workout>)

}