package com.merttutsak.starter.utility.helper.analytics

import com.merttutsak.starter.ui.common.base.view.activity.BaseActivity

interface AnalyticsHelper {

    fun setScreen(
        screenName: String,
        activity: BaseActivity<*,*>?
    )

    fun event(
        event: AnalyticsActions.EVENT,
        activity: BaseActivity<*,*>?
    )

    fun setUserSignedIn(isSignedIn: Boolean)

    fun setUserRegistered(isRegistered: Boolean)
}

object AnalyticsActions {

    enum class EVENT {

        /** These are examples */
        CLICK_SEND_PASSWORD {
            override fun eventId() = "click_send_password"
            override fun eventName() = "Şifre Gönder"
            override fun eventType() = "button_type"
        },
        SCAN_QR_CODE {
            override fun eventId() = "scan_qr_code"
            override fun eventName() = "Read QR Code"
            override fun eventType() = "qr_code_type"
        };

        abstract fun eventId(): String
        abstract fun eventName(): String
        abstract fun eventType(): String
    }

    object SCREEN {
        const val LOGIN = "screen_login"
    }

    // Settings Actions ....
    const val ENABLE = "Enabled"
    const val DISABLE = "Disabled"
    const val UPROP_USER_SIGNED_IN = "user_signed_in"
    const val UPROP_USER_REGISTERED = "user_registered"
}
