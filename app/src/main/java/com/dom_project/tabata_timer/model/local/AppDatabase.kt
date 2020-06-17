package com.dom_project.tabata_timer.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dom_project.tabata_timer.model.local.AppDatabase.Companion.DB_VERSION
import com.dom_project.tabata_timer.model.local.converter.DateConverter
import com.dom_project.tabata_timer.model.local.dao.CircuitProgramDao
import com.dom_project.tabata_timer.model.local.entity.*

/**
 * 기본적인 room db의 구현 클래스
 * DB_NAME을 변경하여 사용하면 된다.
 */
@Database(entities = [Circuit::class, Workout::class, CircuitWorkoutJoin::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun circuitProgramDao(): CircuitProgramDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "base_demo.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_TO_2)
                .build()

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}