package com.merttutsak.starter.utility

import android.Manifest

object Constants {

    object App {
        val TIMESTAMP_FORMAT = "dd/MM/yyyy-HH:mm:ss"
        internal val APP_DB_NAME = "base_starter_db"
        internal val PREF_NAME = "base_starter_pref"

        enum class LoggedInMode constructor() {
            LOGGED_IN_MODE_LOGGED_OUT,
            LOGGED_IN_MODE_GOOGLE,
            LOGGED_IN_MODE_FB,
            LOGGED_IN_MODE_SERVER,
            LOGGED_IN_MODE_LOCAL
        }
    }

    object ArgumentsConstants {
        val ARG_TAG = "TAG"
    }

    object RequestCode

    object Permission

    object View {
        val STATUS_TYPE_DISABLE = "0"
        val STATUS_TYPE_ACTIVE = "1"

        const val SNACKBAR = "SNACKBAR"
        const val SNACKBAR_SUCCESS = "SNACKBAR_SUCCESS"
        const val SNACKBAR_ERROR = "SNACKBAR_ERROR"
        const val SNACKBAR_WARNING = "SNACKBAR_WARNING"
        const val SNACKBAR_INFO = "SNACKBAR_INFO"

        const val OVERLAY = "OVERLAY"
        const val POPUP = "POPUP"
        const val TOAST = "TOAST"
    }

    object RegExp

}