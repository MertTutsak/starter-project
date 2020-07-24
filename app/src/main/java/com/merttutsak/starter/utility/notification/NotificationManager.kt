package com.merttutsak.starter.utility.notification

import com.google.firebase.messaging.FirebaseMessaging
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationManagerCompat
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationChannel
import android.os.Build
import com.google.gson.Gson
import android.app.PendingIntent
import android.content.Intent
import android.app.Notification
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import androidx.core.app.NotificationCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.RemoteMessage
import com.merttutsak.starter.R
import com.merttutsak.starter.ui.landing.LandingPageActivity
import com.merttutsak.starter.utility.extension.isNotNull
import com.orhanobut.logger.Logger
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class NotificationManager {
    val ARGS_PUSHTYPE = "pushType"
    val EXTRA_NOTIFICATION_ID = "EXTRA_NOTIFICATION_ID"

    val PUSH_TYPE_INTERACTIVE = "interactive"
    val PUSH_TYPE_RESPONSIVE = "responsive"
    val PUSH_TYPE_GENERAL = "general"

    fun handleNotification(remoteMessage: RemoteMessage?, context: Context) {
        var title: String? = ""
        var body: String? = ""
        var tyype: String? = ""
        var button1: String? = ""
        var button1Url: String? = ""
        var button2: String? = ""
        var button2Url: String? = ""
        var link: String? = ""
        var bigImageUrl: String? = ""
        if (remoteMessage.isNotNull()) {
            if (remoteMessage?.data?.get("title").isNotNull()) {
                title = remoteMessage?.data?.get("title")
            }
            if (remoteMessage?.data?.get("message").isNotNull()) {
                body = remoteMessage?.data?.get("message")
            }
            if (remoteMessage?.data?.get("type").isNotNull()) {
                tyype = remoteMessage?.data?.get("type")
            }
            if (remoteMessage?.data?.get("button_1").isNotNull()) {
                button1 = remoteMessage?.data?.get("button_1")
            }
            if (remoteMessage?.data?.get("button_1_url").isNotNull()) {
                button1Url = remoteMessage?.data?.get("button_1_url")
            }
            if (remoteMessage?.data?.get("button_2").isNotNull()) {
                button2 = remoteMessage?.data?.get("button_2")
            }
            if (remoteMessage?.data?.get("button_2_url").isNotNull()) {
                button2Url = remoteMessage?.data?.get("button_2_url")
            }
            if (remoteMessage?.data?.get("ins_dl_external").isNotNull()) {
                link = remoteMessage?.data?.get("ins_dl_external")
            }
            if (remoteMessage?.data?.get("image_url").isNotNull()) {
                bigImageUrl = remoteMessage?.data?.get("image_url")
            }
            val lotusNotification = BaseNotification(
                body,
                title,
                tyype,
                button1,
                button1Url,
                button2,
                button2Url,
                link,
                bigImageUrl
            )
            createNotification(context, lotusNotification)
        }
    }

    private fun createNotification(context: Context, lotusNotification: BaseNotification) {
        val r = Random()
        val notificationId = r.nextInt(10000 - 10) + 10
        val channelId = createNotificationChannel(context)
        val mBuilder = NotificationCompat.Builder(context, channelId)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            /*.setSmallIcon(R.mipmap.ic_logo)*/ //set logo
            .setAutoCancel(false)
            .setWhen(0)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)

        if (lotusNotification.title.isNotNull()) {
            mBuilder.setContentTitle(lotusNotification.title)
        }
        if (lotusNotification.body.isNotNull()) {
            mBuilder.setContentText(lotusNotification.body)
        }
        if (lotusNotification.type.isNotNull() && lotusNotification.type == PUSH_TYPE_INTERACTIVE) {
            if (lotusNotification.button1.isNotNull()) {
                val intent = Intent(context, LandingPageActivity::class.java)
                if (lotusNotification.button1Url.isNotNull()) {
                    intent.data = Uri.parse(lotusNotification.button1Url)
                }
                intent.putExtra(ARGS_PUSHTYPE, lotusNotification.type)
                intent.putExtra(EXTRA_NOTIFICATION_ID, notificationId)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                val button1Intent =
                    PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                mBuilder.addAction(0, lotusNotification.button1, button1Intent)
            }
            if (lotusNotification.button2.isNotNull()) {
                val intent = Intent(context, LandingPageActivity::class.java)
                if (lotusNotification.button2Url.isNotNull()) {
                    intent.data = Uri.parse(lotusNotification.button2Url)
                }
                intent.putExtra(ARGS_PUSHTYPE, lotusNotification.type)
                intent.putExtra(EXTRA_NOTIFICATION_ID, notificationId)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                val button2Intent =
                    PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                mBuilder.addAction(0, lotusNotification.button2, button2Intent)
            }
        }
        if (lotusNotification.link.isNotNull()) {
            val intent = Intent(context, LandingPageActivity::class.java)
            intent.putExtra(ARGS_PUSHTYPE, lotusNotification.type)
            intent.putExtra(EXTRA_NOTIFICATION_ID, notificationId)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.data = Uri.parse(lotusNotification.link)
            val contentIntent =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            mBuilder.setContentIntent(contentIntent)
        }

        val notification = mBuilder.build()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, notification)
        if (lotusNotification.bigImageUrl.isNotNull() && lotusNotification.bigImageUrl.isNullOrEmpty()) {
            var title = ""
            var desc = ""
            if (lotusNotification.title.isNotNull()) {
                title = lotusNotification.title ?: ""
            }
            if (lotusNotification.body.isNotNull()) {
                desc = lotusNotification.body ?: ""
            }
            BigImageLoader(
                mBuilder,
                notificationManager,
                notificationId,
                title,
                desc
            ).execute(lotusNotification.bigImageUrl)
        }
        try {
            Logger.json(Gson().toJson(lotusNotification, BaseNotification::class.java))
        } catch (e: Exception) {
            Logger.e("Broken push object")
        }

    }

    private fun createNotificationChannel(context: Context): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_service"
            val channelName = "My Notification Service"
            val chan = NotificationChannel(
                channelId,
                channelName,
                android.app.NotificationManager.IMPORTANCE_HIGH
            )
            chan.lightColor = Color.WHITE
            chan.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val service =
                context.getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
            service.createNotificationChannel(chan)
            return channelId
        }
        return context.resources.getString(R.string.default_notification_channel_id)
    }

    private class BigImageLoader(
        private val builder: NotificationCompat.Builder?,
        private val notificationManager: NotificationManagerCompat,
        private val notificationId: Int,
        private val title: String,
        private val description: String
    ) : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg params: String): Bitmap? {
            val `in`: InputStream
            try {
                val url = URL(params[0])
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                `in` = connection.inputStream
                return BitmapFactory.decodeStream(`in`)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            super.onPostExecute(bitmap)
            if (bitmap.isNotNull() && builder.isNotNull()) {
                builder?.setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap).bigLargeIcon(null).setBigContentTitle(title)
                        .setSummaryText(
                            description
                        )
                )
                notificationManager.notify(notificationId, builder!!.build())
            }
        }
    }

    fun subscribeToTopic(topic: String, errorFunc: () -> Unit) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    errorFunc()
                }
            }.addOnCanceledListener {
                errorFunc()
            }
    }

    fun getCurrentNotifyToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Logger.w("NotificationManager", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Logger.d("NotificationManager", token)
            })
    }
}