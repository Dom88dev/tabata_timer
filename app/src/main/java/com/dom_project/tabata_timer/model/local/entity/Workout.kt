package com.dom_project.tabata_timer.model.local.entity

import androidx.room.*

@Entity(tableName = "workouts", indices = [Index(value = ["name"], name = "workout_name", unique = true)])
data class Workout(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    @ColumnInfo(name = "how_to") var howTo: String,
    var thumbnail: String
) {
}