package com.merttutsak.starter.utility.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        NotificationManager().handleNotification(p0, applicationContext)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        /*sendRegistrationToServer(token);*/
    }
}