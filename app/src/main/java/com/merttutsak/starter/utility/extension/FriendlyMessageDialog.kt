package com.merttutsak.starter.utility.extension

import android.app.AlertDialog
import android.content.Context
import com.merttutsak.starter.R
import com.merttutsak.starter.data.remote.model.base.ButtonSet
import com.merttutsak.starter.data.remote.model.base.FriendlyMessage
import com.merttutsak.starter.data.remote.model.base.ProcessStatus
import com.merttutsak.starter.utility.Constants
import com.merttutsak.starter.utility.extension.isNull
import com.merttutsak.starter.utility.extension.notNull
import com.merttutsak.starter.utility.extension.showToast

fun Context.showFriendlyMessage(
    friendlyMessage: FriendlyMessage?
) {
    if (friendlyMessage.isNull()) {
        return
    }

    friendlyMessage?.run {
        when (displayType) {
            Constants.View.POPUP -> {
                this@showFriendlyMessage.showDefaultAlertDialog(
                    positiveButton?.text,
                    negativeButton?.text,
                    title ?: "",
                    description ?: "",
                    cancelable ?: false
                )
            }
            else -> {
                this@showFriendlyMessage.showToast(description)
            }
        }
        return
    }

    this.showDefaultAlertDialog(
        getString(R.string.label_okey),
        null,
        getString(R.string.dialog_default_warning_title),
        getString(R.string.dialog_default_warning_desc)
    )
}

fun Context.showDefaultAlertDialog(
    btnPositive: String?,
    btnNegative: String?,
    title: String,
    desc: String,
    isCancelable: Boolean = false
): AlertDialog {
    val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle)
    builder.setTitle(title)
    builder.setMessage(desc)
    builder.setCancelable(isCancelable)

    btnPositive.notNull {
        builder.setPositiveButton(it) { dialog, which ->
            dialog.dismiss()
        }
    }

    btnNegative.notNull {
        builder.setNegativeButton(it) { dialog, which ->
            dialog.dismiss()
        }
    }

    val dialog: AlertDialog = builder.create()
    dialog.show()
    return dialog
}