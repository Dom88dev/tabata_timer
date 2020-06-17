package com.dom_project.tabata_timer.model.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

/*
 db에 저장되는 table(entity)가 아니기 때문에 @Entity 주석은 달지 않는다.
 Room 2.2.0 버젼부터 @Relation에는 새 @Junction 주석을 사용하는 새 associateBy 속성이 있으며 이 속성은 연결 테이블(조인 테이블이라고도 함)을 통해 충족되어야 하는 관계를 선언하고
 이를 이용해 workouts 라는 Workout의 리스트를 반환받을 수 있다..
 */
data class CircuitProgram(
    @Embedded val circuit: Circuit,
    @Relation(
        parentColumn = "id",
        entity = Workout::class,
        entityColumn = "id",
        associateBy = Junction(
            value = CircuitWorkoutJoin::class,
            parentColumn = "circuit_id",
            entityColumn = "workout_id"
        )
    )
    var workouts: List<Workout>
) {
}