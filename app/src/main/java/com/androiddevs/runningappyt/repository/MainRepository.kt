package com.androiddevs.runningappyt.repository

import com.androiddevs.runningappyt.db.Run
import com.androiddevs.runningappyt.db.RunDAO
import com.androiddevs.runningappyt.other.TrackingUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.round

class MainRepository @Inject constructor(
    private val runDao: RunDAO
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate().flowOn(Dispatchers.IO)

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance().flowOn(Dispatchers.IO)

    fun getAllRunsSortedByTimeInMillis() =
        runDao.getAllRunsSortedByTimeInMillis().flowOn(Dispatchers.IO)

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed().flowOn(Dispatchers.IO)

    fun getAllRunsSortedByCaloriesBurned() =
        runDao.getAllRunsSortedByCaloriesBurned().flowOn(Dispatchers.IO)

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed().map {
        "${round(it * 10f) / 10f}Km/h"
    }.flowOn(Dispatchers.IO)

    fun getTotalDistance() = runDao.getTotalDistance().map {
        "${round(((it / 1000f) * 10f) / 10f)}Km"
    }.flowOn(Dispatchers.IO)

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned().map {
        "${it}Kcal"
    }.flowOn(Dispatchers.IO)

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis().map {
        TrackingUtility.getFormattedStopWatchText(it)
    }.flowOn(Dispatchers.IO)
}