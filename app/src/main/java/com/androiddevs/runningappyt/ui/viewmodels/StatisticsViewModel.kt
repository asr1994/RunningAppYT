package com.androiddevs.runningappyt.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.androiddevs.runningappyt.repository.MainRepository

class StatisticsViewModel @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {

    val avgSpeed = repository.getTotalAvgSpeed().asLiveData()

    val totalCaloriesBurned = repository.getTotalCaloriesBurned().asLiveData()

    val totalDistance = repository.getTotalDistance().asLiveData()

    val totalTime = repository.getTotalTimeInMillis().asLiveData()

    val runsSortedByDate = repository.getAllRunsSortedByDate().asLiveData()

}