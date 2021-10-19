package com.example.mvipattern.utilities

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.mvipattern.R

class LoadingDialog(private val context: Activity) {

    fun build(): AlertDialog {

        val view = context.layoutInflater.inflate(R.layout.dialog_loading, null)

        val dialog = AlertDialog.Builder(context)
            .create()

        dialog.setCanceledOnTouchOutside(false)
        dialog.setView(view)

        return dialog
    }
}