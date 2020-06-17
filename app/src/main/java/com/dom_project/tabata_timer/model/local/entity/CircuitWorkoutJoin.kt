package com.dom_project.tabata_timer.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "circuit_workout_join",
    primaryKeys = ["circuit_id", "workout_id"],
    foreignKeys = [
    ForeignKey(entity = Circuit::class, parentColumns = ["id"], childColumns = ["circuit_id"]),
    ForeignKey(entity = Workout::class, parentColumns = ["id"], childColumns = ["workout_id"])
    ])
data class CircuitWorkoutJoin(
    @ColumnInfo(name = "circuit_id") val circuitId: Long,
    @ColumnInfo(name = "workout_id") val workoutId: Long,
    var order: Int
) {
}