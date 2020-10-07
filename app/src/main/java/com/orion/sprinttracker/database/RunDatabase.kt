package com.orion.sprinttracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.orion.sprinttracker.data.Run
import com.orion.sprinttracker.data.RunDao

@Database(entities = [Run::class], version = 1, exportSchema = false)
abstract class RunDatabase : RoomDatabase() {
    abstract fun getNoteDao(): RunDao

    companion object {
        private var instance: RunDatabase? = null

        @Synchronized
        fun getDatabase(context: Context?): RunDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context!!,
                    RunDatabase::class.java,
                    "notes_db"
                ).build()
            }
            return instance
        }
    }
}