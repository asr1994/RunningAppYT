package com.androiddevs.runningappyt.ui.viewmodels

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.androiddevs.runningappyt.di.KeyName
import com.androiddevs.runningappyt.di.KeyWeight
import com.androiddevs.runningappyt.other.Constants.KEY_NAME
import com.androiddevs.runningappyt.other.Constants.KEY_WEIGHT

class SettingsViewModel @ViewModelInject constructor(
    val sharedPrefs: SharedPreferences,
    @KeyName val name: String,
    @KeyWeight val weight: Float
) : ViewModel() {

    fun applyChanges(name: String, weight: String): Boolean {
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }

        sharedPrefs.edit {
            putString(KEY_NAME, name)
            putFloat(KEY_WEIGHT, weight.toFloat())
        }

        return true
    }

}