package com.merttutsak.starter.utility.extension

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.merttutsak.starter.R
import java.util.*

/**Example**/
/**
 * context.callThePhone("5554443322")
 * context.callThePhone("555 444 33 22")
 * context.callThePhone("05554443322")
 * context.callThePhone("0 555 444 33 22")
 * context.callThePhone("+905554443322")
 * context.callThePhone("+90 555 444 33 22")
 *
 * The context variable can be null
*/
fun Context?.callThePhone(phone: String) {
    if (this.isNull()) return
    val uri = Uri.parse("tel:$phone")
    val it = Intent(Intent.ACTION_DIAL, uri)
    this?.startActivity(it)
}

/**Example**/
/**
 *  context.sendEMail("merttutsak@merttutsak.com")
 * context.sendEMail("merttutsak@merttutsak.com.tr")
 *
 * The context variable can be null
*/
fun Context?.sendEMail(email: String) {
    if (this.isNull()) return
    val uri = Uri.parse("mailto:$email")
    val it = Intent(Intent.ACTION_SENDTO, uri)
    this?.startActivity(it)
}
/**Example**/
/**
 * context.sendSMS("5554443322")
 * context.sendSMS("555 444 33 22")
 * context.sendSMS("05554443322")
 * context.sendSMS("0 555 444 33 22")
 * context.sendSMS("+905554443322")
 * context.sendSMS("+90 555 444 33 22")
 *
 *  The context variable can be null
*/
fun Context?.sendSMS(phone: String) {
    if (this.isNull()) return
    val uri = Uri.parse("smsto:$phone")
    val it = Intent(Intent.ACTION_SENDTO, uri)
    this?.startActivity(it)
}

fun Context?.launchDirections(latitude: Double, longitude: Double) {
    if (this.isNull()) return

    try {
        val navigationIntentUri = Uri.parse("google.navigation:q=$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, navigationIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        this!!.startActivity(mapIntent)
    } catch (e: ActivityNotFoundException) {
        val uri: String = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(uri)
        }
        if (intent.resolveActivity(this!!.packageManager) != null) {
            this!!.startActivity(intent)
        }
    }
}

fun Context?.openPDF(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    this?.startActivity(i)
}

fun Context?.share(text: String, subject: String = "", title: String = "") {
    val sharingIntent = Intent(Intent.ACTION_SEND)

    sharingIntent.type = "text/plain"
    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text)
    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)

    /*val clipboardIntent = Intent(this, CopyToClipboardActivity::class.java)
    clipboardIntent.data = Uri.parse(text)*/

    val chooserIntent = Intent.createChooser(
        sharingIntent,
        if (title.isNullOrEmpty()) this?.getString(R.string.intent_share_title) ?: "" else title
    )

    /*chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(clipboardIntent))*/

    this?.startActivity(chooserIntent)
}

fun Context.redirectStore(){
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packageName")
            )
        )
    } catch (anfe: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }
}