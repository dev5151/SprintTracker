package com.orion.sprinttracker.database

import android.content.Context
import androidx.room.*
import com.orion.sprinttracker.data.Converters
import com.orion.sprinttracker.data.Run
import com.orion.sprinttracker.data.RunDao

@Database(entities = [Run::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class RunDatabase : RoomDatabase() {
    abstract fun getRunDao(): RunDao

}