package com.androiddevs.runningappyt.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.other.Constants.KEY_NAME
import com.androiddevs.runningappyt.ui.viewmodels.SettingsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            etName.setText(name)
            etWeight.setText(weight.toString())
        }

        btnApplyChanges.setOnClickListener {
            val changed = viewModel.applyChanges(etName.text.toString(), etWeight.text.toString())

            if (changed) {
                Snackbar.make(requireView(), "Saved changes", Snackbar.LENGTH_LONG).show()

                requireActivity().tvToolbarTitle.text = "Welcome back ${viewModel.sharedPrefs.getString(KEY_NAME, "")}!"

            } else {
                Snackbar.make(requireView(), "Please fill all inputs", Snackbar.LENGTH_LONG).show()
            }
        }

    }

}