package com.orion.sprinttracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.orion.sprinttracker.data.RunDao
import com.orion.sprinttracker.database.RunDatabase
import com.orion.sprinttracker.utils.Constants.Companion.DATABASE_NAME
import com.orion.sprinttracker.utils.Constants.Companion.KEY_FIRST_TIME_TOGGLE
import com.orion.sprinttracker.utils.Constants.Companion.KEY_NAME
import com.orion.sprinttracker.utils.Constants.Companion.KEY_WEIGHT
import com.orion.sprinttracker.utils.Constants.Companion.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * AppModule, provides application wide singletons
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): RunDatabase {
        return Room.databaseBuilder(app, RunDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRunDao(db: RunDatabase): RunDao {
        return db.getRunDao()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPreferences: SharedPreferences) =
        sharedPreferences.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPreferences: SharedPreferences) = sharedPreferences.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true
    )


}