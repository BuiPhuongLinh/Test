package com.example.widgetcommon.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import com.example.widgetcommon.R

class DialogError(context: Context) {
    private val alertDialog: AlertDialog?
    private val tvError: TextView
    private val tvOK: TextView

    init {
        val builder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_error, null)
        builder.setView(dialogView)
        alertDialog = builder.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCancelable(false)
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        tvError = dialogView.findViewById(R.id.tvError)
        tvOK = dialogView.findViewById(R.id.tvAccept)
    }

    fun showDialog(error: String) {
        tvError.text = error
        tvOK.setOnClickListener { alertDialog!!.dismiss() }
        alertDialog!!.show()
    }

    fun showDialogRating(content: String, button: String, listener: DialogListener) {
        tvError.text = content
        tvOK.text = button
        tvOK.setOnClickListener {
            alertDialog!!.dismiss()
            listener.backToMain()
        }
        alertDialog!!.show()
    }

    fun hideDialog() {
        if (alertDialog != null && alertDialog.isShowing) {
            alertDialog.dismiss()
        }
    }

    interface DialogListener {
        fun backToMain()
    }
}