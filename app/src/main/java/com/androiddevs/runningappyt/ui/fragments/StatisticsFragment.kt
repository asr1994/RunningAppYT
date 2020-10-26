package com.androiddevs.runningappyt.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    private fun subscribe() {
        viewModel.apply {
            avgSpeed.observe(viewLifecycleOwner, {
                tvAverageSpeed.text = it
            })

            totalCaloriesBurned.observe(viewLifecycleOwner, {
                tvTotalCalories.text = it
            })

            totalDistance.observe(viewLifecycleOwner, {
                tvTotalDistance.text = it
            })

            totalTime.observe(viewLifecycleOwner, {
                tvTotalTime.text = it
            })
        }
    }

}