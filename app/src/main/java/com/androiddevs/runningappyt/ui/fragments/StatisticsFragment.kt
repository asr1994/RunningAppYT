package com.androiddevs.runningappyt.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.ui.viewmodels.StatisticsViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBarChart()

        subscribe()
    }

    private fun setupBarChart() {
        barChart.apply {

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                axisLineColor = Color.WHITE
                textColor = Color.WHITE
                setDrawLabels(false)
                setDrawGridLines(false)
            }

            axisLeft.apply {
                axisLineColor = Color.WHITE
                textColor = Color.WHITE
                setDrawGridLines(false)
            }

            axisRight.apply {
                axisLineColor = Color.WHITE
                textColor = Color.WHITE
                setDrawGridLines(false)
            }

            description.text = "Average Speed Over Time"
            legend.isEnabled = false
        }
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

            runsSortedByDate.observe(viewLifecycleOwner, {
                it?.let {
                    val allAvgSpeeds = it.indices.map { i -> BarEntry(i.toFloat(), it[i].avgSpeedInKMH) }

                    val dataSet = BarDataSet(allAvgSpeeds, "").apply {
                        valueTextColor = Color.WHITE
                        color = ContextCompat.getColor(requireContext(), R.color.colorAccent)
                    }

                    barChart.apply {
                        data = BarData(dataSet)
                        invalidate()
                    }
                }
            })

        }
    }

}