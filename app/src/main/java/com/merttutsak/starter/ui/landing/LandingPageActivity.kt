package com.merttutsak.starter.ui.landing

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.merttutsak.starter.utility.notification.NotificationManager
import com.merttutsak.starter.utility.provider.DeeplinkProvider

class LandingPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val notificationManager: NotificationManager = NotificationManager()
        if (intent.getStringExtra(notificationManager.ARGS_PUSHTYPE) != null) {
            var type = intent.getStringExtra(notificationManager.ARGS_PUSHTYPE)
            if (type != null && type == NotificationManager().PUSH_TYPE_RESPONSIVE) {
                // open browser
                /*new ActivityTransition.Builder(this, AppBrowserActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .setTransition(new Transition.TransitionSlideUpDown())
                    .setData(getIntent().getData())
                    .build()
                    .start();*/
            } else {
                DeeplinkProvider().init(this, intent)
            }
        } else {
            DeeplinkProvider().init(this, intent)
        }

        if (intent.extras != null) {
            val notifId = intent.getIntExtra(NotificationManager().EXTRA_NOTIFICATION_ID, -1)
            (getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager).cancel(notifId)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}