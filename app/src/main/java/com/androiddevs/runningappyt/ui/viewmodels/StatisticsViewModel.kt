package com.androiddevs.runningappyt.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.androiddevs.runningappyt.repository.MainRepository

class StatisticsViewModel @ViewModelInject constructor(
    repository: MainRepository
) : ViewModel() {
}