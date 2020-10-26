package com.androiddevs.runningappyt.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.androiddevs.runningappyt.db.Run
import com.androiddevs.runningappyt.di.KeyWeight
import com.androiddevs.runningappyt.other.SortType
import com.androiddevs.runningappyt.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    @KeyWeight val weight: Float
) : ViewModel() {

    private val runsSortedByDate = repository.getAllRunsSortedByDate()
    private val runsSortedByAvgSpeed = repository.getAllRunsSortedByAvgSpeed()
    private val runsSortedByCaloriesBurned = repository.getAllRunsSortedByCaloriesBurned()
    private val runsSortedByDistance = repository.getAllRunsSortedByDistance()
    private val runsSortedByTimeInMillis = repository.getAllRunsSortedByTimeInMillis()

    private val sortType = MutableLiveData<SortType>()

    val runs: LiveData<List<Run>> = Transformations.switchMap(sortType) {
        when (it) {
            SortType.DATE -> runsSortedByDate
            SortType.AVG_SPEED -> runsSortedByAvgSpeed
            SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned
            SortType.DISTANCE -> runsSortedByDistance
            SortType.RUNNING_TIME -> runsSortedByTimeInMillis
            else -> runsSortedByDate
        }
    }

    fun sort(type: SortType) {
        sortType.value = type
    }

    fun insertRun(run: Run) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRun(run)
        }
    }
}