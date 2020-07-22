package com.merttutsak.starter.utility.provider

import android.content.Intent
import android.app.Activity
import android.net.Uri
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.orhanobut.logger.Logger


class DeeplinkProvider {

    var deepLink: Uri? = null
        private set
    var path: String? = null
    var param1: String? = null
    var param2: String? = null
    var param3: String? = null
    private var aClass: Class<*>? = null
    private var activityClassName: String? = null
    private var paramsDeepLink: List<String>? = null

    fun init(currentActivity: Activity, deeplink: String) {
        controlDeeplink(currentActivity, Uri.parse(deeplink))
    }

    fun init(currentActivity: Activity, intent: Intent) {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(currentActivity) { pendingDynamicLinkData ->
                val deepLink: Uri
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link!!
                    controlDeeplink(currentActivity, deepLink)
                } else {
                    controlDeeplink(currentActivity, intent.data)

                }
            }
            .addOnFailureListener(currentActivity) { e ->
                Logger.w("Deeplink", "getDynamicLink:onFailure", e)
                controlDeeplink(currentActivity, intent.data) }
    }

    private fun controlDeeplink(currentActivity: Activity, deepLink: Uri?) {
        this.deepLink = deepLink
        //redirect main or splash
//        currentActivity.startActivity(Intent(currentActivity,TestActivity::class.java))
//        if (currentActivity is LandingPageActivity) {
//            currentActivity.finish()
//        }
    }
}