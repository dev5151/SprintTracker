package com.orion.sprinttracker.repository

import androidx.lifecycle.LiveData
import com.orion.sprinttracker.data.Run
import com.orion.sprinttracker.data.RunDao

class RunRepository(private val runDao: RunDao) {

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate(): LiveData<List<Run>> {
        return runDao.getAllRunsSortedByDate()
    }

    fun etAllRunsSortedByCaloriesBurned(): LiveData<List<Run>> {
        return runDao.getAllRunsSortedByCaloriesBurned()
    }


    fun getAllRunsSortedByDistance(): LiveData<List<Run>> {
        return runDao.getAllRunsSortedByDistance()
    }


    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>> {
        return runDao.getAllRunsSortedByAvgSpeed()
    }

    fun getTotalTimeInMillis(): LiveData<Long> {
        return runDao.getTotalTimeInMillis()
    }

    fun getTotalDistance(): LiveData<Int> {
        return runDao.getTotalDistance()
    }

    fun getTotalAvgSpeed(): LiveData<Float> {
        return runDao.getTotalAvgSpeed()
    }

    fun getTotalCaloriesBurned(): LiveData<Long> {
        return runDao.getTotalCaloriesBurned()
    }

}