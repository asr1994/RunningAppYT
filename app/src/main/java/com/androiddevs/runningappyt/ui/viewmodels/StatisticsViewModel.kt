package com.androiddevs.runningappyt.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.androiddevs.runningappyt.other.TrackingUtility
import com.androiddevs.runningappyt.repository.MainRepository
import kotlin.math.round

class StatisticsViewModel @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    val avgSpeed = repository.getTotalAvgSpeed().map {
        "${round(it * 10f) / 10f}Km/h"
    }

    val totalCaloriesBurned = repository.getTotalCaloriesBurned().map {
        "${it}Kcal"
    }

    val totalDistance = repository.getTotalDistance().map {
        "${round(((it / 1000f) * 10f) / 10f)}Km"
    }

    val totalTime = repository.getTotalTimeInMillis().map {
        TrackingUtility.getFormattedStopWatchText(it)
    }

}