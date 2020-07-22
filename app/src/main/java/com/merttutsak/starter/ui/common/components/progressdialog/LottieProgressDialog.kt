package com.merttutsak.starter.ui.common.components.progressdialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.merttutsak.starter.R

class LottieProgressDialog(context: Context) :
    Dialog(context, R.style.ProgressDialogTheme) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.progress_dialog)
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
        //clearOverlay() //Gölgeyi silmek için commenti kaldır.
    }

    private fun clearOverlay() {
        this.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    override fun show() {
        if (!isShowing) {
            super.show()
        }
    }

    override fun dismiss() {
        if (isShowing) {
            super.dismiss()
        }
    }


    override fun cancel() {
        if (isShowing) {
            super.cancel()
        }
    }
}