package com.androiddevs.runningappyt.other

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.androiddevs.runningappyt.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CancelRunDialog : DialogFragment() {

    private var yesListener: (() -> Unit)? = null

    fun setOnYesListener(listener: () -> Unit) {
        yesListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Cancel the Run")
            .setMessage("Are you sure to cancel the run and delete all of its data?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Yes") { _, _ ->
                yesListener?.invoke()
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }

}