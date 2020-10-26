package com.androiddevs.runningappyt.ui.viewmodels

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.androiddevs.runningappyt.di.KeyFirstOpen
import com.androiddevs.runningappyt.other.Constants

class SetupViewModel @ViewModelInject constructor(
    private val sharedPrefs: SharedPreferences,
    @KeyFirstOpen val isFirstOpen: Boolean
) : ViewModel() {

    fun writePersonalInfoToSharedPreferences(name: String, weight: String): Boolean {
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }

        sharedPrefs.edit {
            putString(Constants.KEY_NAME, name)
            putFloat(Constants.KEY_WEIGHT, weight.toFloat())
            putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, false)
        }

        return true
    }

}