package com.androiddevs.runningappyt.repository

import androidx.lifecycle.asLiveData
import com.androiddevs.runningappyt.db.Run
import com.androiddevs.runningappyt.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDao: RunDAO
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate().asLiveData()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance().asLiveData()

    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis().asLiveData()

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed().asLiveData()

    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned().asLiveData()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed().asLiveData()

    fun getTotalDistance() = runDao.getTotalDistance().asLiveData()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned().asLiveData()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis().asLiveData()
}