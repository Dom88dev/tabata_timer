package com.dom_project.tabata_timer.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "circuits", indices = [Index(value = ["name"], name = "circuit_name", unique = true)])
data class Circuit(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    @ColumnInfo(name = "workout_time") var workoutTime: Int = 20,
    @ColumnInfo(name = "rest_time") var restTime: Int = 10,
    @ColumnInfo(name = "repeat_count") var repeatCount: Int = 1
) {

}