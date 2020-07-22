package com.merttutsak.starter.utility.utils

import com.merttutsak.starter.data.remote.model.base.FriendlyMessage
import com.merttutsak.starter.data.remote.model.base.ProcessStatus
import com.merttutsak.starter.ui.common.base.view.activity.BaseActivity
import com.merttutsak.starter.utility.Constants

object DialogUtils {

    fun showFriendlyMessage(
        base: BaseActivity<*,*>?,
        friendlyMessage: FriendlyMessage?,
        processStatus: ProcessStatus?
    ) {
        if (base != null) {
            if (friendlyMessage?.displayType != null) {
                when (friendlyMessage.displayType) {
                    Constants.View.SNACKBAR -> if (processStatus != null) {
                        if (processStatus.isSuccess) {

                        } else {

                        }
                    }
//                    Constants.View.SNACKBAR_SUCCESS ->
//                    Constants.View.SNACKBAR_ERROR ->
//                    Constants.View.SNACKBAR_WARNING ->
//                    Constants.View.SNACKBAR_INFO ->
//                    Constants.View.OVERLAY ->
//                    Constants.View.TOAST ->
//                    Constants.View.POPUP ->
                }
            }
        }
    }

//    fun showNetworkError(context: Context?, retryCallback: LotusService.RetryCallback?) {
//        if (context != null) {
////            CUSTOMDIALOG(context)
////                .setTitle(context.getString(R.string.network_error_title))
////                .setDescription(context.getString(R.string.network_error_desc))
////                .setIcon(R.drawable.ic_network)
////                .setCancelable(false)
////                .setPositiveButton(context.getString(R.string.retry), { CUSTOMDIALOG ->
////                    retryCallback?.retry()
////                }).show()
//        }
//    }

//    fun showDefaultAlertDialog(
//        context: Context?,
//        btnPositive: String?,
//        btnNegative: String?,
//        title: String?,
//        dect: String?
//    ): AlertDialog {
//        val builder = AlertDialog.Builder(context!!, R.style.AlertDialog)
//        builder.setTitle(title)
//        builder.setMessage(dect)
//        builder.setPositiveButton(btnPositive) { dialog, which ->
//
//        }
//        builder.setNegativeButton(btnNegative) { dialog, which ->
//            dialog.dismiss()
//        }
//        val dialog: AlertDialog = builder.create()
//        dialog.show()
//        return dialog
//    }
}