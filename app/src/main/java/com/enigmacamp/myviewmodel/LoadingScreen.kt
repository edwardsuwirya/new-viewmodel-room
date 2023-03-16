package com.enigmacamp.myviewmodel

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window

object LoadingScreen {
    var dialog: Dialog? = null
    fun show(context: Context, cancelable: Boolean) {
        dialog = Dialog(context)
        dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.loading_dialog)
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(cancelable)
            show()
        }
    }

    fun hide() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }
}