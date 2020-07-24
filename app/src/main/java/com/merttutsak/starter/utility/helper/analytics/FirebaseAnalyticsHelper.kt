package com.merttutsak.starter.utility.helper.analytics

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableInt
import com.google.firebase.analytics.FirebaseAnalytics
import com.merttutsak.starter.ui.common.base.view.activity.BaseActivity
import com.merttutsak.starter.utility.helper.analytics.AnalyticsActions.UPROP_USER_REGISTERED
import com.merttutsak.starter.utility.helper.analytics.AnalyticsActions.UPROP_USER_SIGNED_IN
import javax.inject.Inject

class FirebaseAnalyticsHelper @Inject constructor(
    context: Context
) : AnalyticsHelper {

    private val TAG = "FIREBASE_ANALYTICS"

    private var firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    init {
        firebaseAnalytics.setAnalyticsCollectionEnabled(true)
        Log.d(TAG, "Analytics initialized")
    }

    /** Example
     * setScreen(AnalyticsActions.SCREEN.LOGIN,this@MAinActivity)
     */
    override fun setScreen(
        screenName: String,
        activity: BaseActivity<*,*>?
    ) {
        activity?.let {
            firebaseAnalytics.run {
                setCurrentScreen(it, screenName, null)
                Log.d(
                    TAG, "Screen View:" +
                            "\nscreen name ->$screenName" +
                            "\nevent type  ->$screenName"
                )
            }
        }
    }

    /** Example
     * event(AnalyticsActions.EVENT.CLICK_SEND_PASSWORD,this@MAinActivity)
     */
    override fun event(
        event: AnalyticsActions.EVENT,
        activity: BaseActivity<*,*>?
    ) {
        val params = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_ID, event.eventId())
            putString(FirebaseAnalytics.Param.ITEM_NAME, event.eventName())
            putString(FirebaseAnalytics.Param.CONTENT_TYPE, event.eventType())
        }
        firebaseAnalytics.run {
            logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params)
            Log.d(
                TAG, "Event:" +
                        "\nevent id    ->${event.eventId()}" +
                        "\nevent name  ->${event.eventName()}" +
                        "\nevent type  ->${event.eventType()}"
            )
        }
    }

    override fun setUserSignedIn(isSignedIn: Boolean) {
        firebaseAnalytics.setUserProperty(UPROP_USER_SIGNED_IN, isSignedIn.toString())
    }

    override fun setUserRegistered(isRegistered: Boolean) {
        firebaseAnalytics.setUserProperty(UPROP_USER_REGISTERED, isRegistered.toString())
    }

    private fun getBooleanPreferenceAction(
        prefs: SharedPreferences,
        key: String
    ): String {
        return if (prefs.getBoolean(key, true)) AnalyticsActions.ENABLE
        else AnalyticsActions.DISABLE
    }

}
