package com.merttutsak.starter.utility.extension

import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.merttutsak.starter.data.local.preferences.AppSharedPrefHelper

var defaultPrefFileName = AppSharedPrefHelper.SHARED_PREF_KEY.CURRENT_LANGUAGE.name
var defaultMode: Int = MODE_PRIVATE

fun Context.setInt(
    intValue: Int, tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
) {
    val sp = this.getSharedPreferences(prefFileName, mode)
    sp.edit().putInt(tag, intValue).apply()
}

fun Context.getInt(
    tag: String, default: Int = 0,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Int {
    return this.getSharedPreferences(prefFileName, mode).getInt(tag, default)
}

fun Context.setBool(
    boolValue: Boolean, tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
) {
    val sp = this.getSharedPreferences(prefFileName, mode)
    sp.edit().putBoolean(tag, boolValue).apply()
}

fun Context.getBool(
    tag: String,
    default: Boolean = false
    ,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Boolean {
    return this.getSharedPreferences(prefFileName, mode).getBoolean(tag, default)
}

fun Context.setString(
    string: String?, tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
) {
    val sp = this.getSharedPreferences(prefFileName, mode)
    sp.edit().putString(tag, string).apply()
}

fun Context.getString(
    tag: String, default: String? = null,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): String? {
    return this.getSharedPreferences(prefFileName, mode).getString(tag, default)
}

fun Context.setLong(
    longValue: Long, tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
) {
    val sp = this.getSharedPreferences(prefFileName, mode)
    sp.edit().putLong(tag, longValue).apply()
}

fun Context.getLong(
    tag: String, default: Long = 0L,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Long {
    return this.getSharedPreferences(prefFileName, mode).getLong(tag, default)
}

fun Context.setFloat(
    floatValue: Float, tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
) {
    val sp = this.getSharedPreferences(prefFileName, mode)
    sp.edit().putFloat(tag, floatValue).apply()
}

fun Context.getFloat(
    tag: String, default: Float = 0F,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Float {
    return this.getSharedPreferences(prefFileName, mode).getFloat(tag, default)
}

fun Context.setStringSet(
    strSet: Set<String?>?, tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
) {
    val sp = this.getSharedPreferences(prefFileName, mode)
    sp.edit().putStringSet(tag, strSet).apply()
}

fun Context.getStringSet(
    tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Set<String?>? {
    return this.getSharedPreferences(prefFileName, mode).getStringSet(tag, null)
}

//fragment
fun Fragment.setInt(intValue: Int, tag: String) = this.context?.setInt(intValue, tag)

fun Fragment.getInt(
    tag: String, default: Int = 0,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Int = this.context?.getInt(tag, default)!!

fun Fragment.setBool(boolValue: Boolean, tag: String) =
    this.context?.setBool(boolValue, tag)

fun Fragment.getBool(tag: String, default: Boolean = false) =
    this.context?.getBool(tag, default)

fun Fragment.setString(string: String?, tag: String) =
    this.context?.setString(string, tag)

fun Fragment.getString(tag: String, default: String? = null) =
    this.context?.getString(tag, default)

fun Fragment.setLong(longValue: Long, tag: String) =
    this.context?.setLong(longValue, tag)

fun Fragment.getLong(tag: String, default: Long = 0L) = this.context?.getLong(tag, default)

fun Fragment.setFloat(floatValue: Float, tag: String) =
    this.context?.setFloat(floatValue, tag)

fun Fragment.getFloat(tag: String, default: Float = 0F) =
    this.context?.getFloat(tag, default)

fun Fragment.setStringSet(strSet: Set<String?>?, tag: String) =
    this.context?.setStringSet(strSet, tag)

fun Fragment.getStringSet(
    tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Set<String?>? = this.context?.getStringSet(tag)

//dialog
fun Dialog.setInt(intValue: Int, tag: String) = this.context.setInt(intValue, tag)

fun Dialog.getInt(
    tag: String, default: Int = 0,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Int = this.context.getInt(tag, default)

fun Dialog.setBool(boolValue: Boolean, tag: String) =
    this.context.setBool(boolValue, tag)

fun Dialog.getBool(tag: String, default: Boolean = false) =
    this.context.getBool(tag, default)

fun Dialog.setString(string: String?, tag: String) = this.context.setString(string, tag)

fun Dialog.getString(tag: String, default: String? = null) =
    this.context.getString(tag, default)

fun Dialog.setLong(longValue: Long, tag: String) = this.context.setLong(longValue, tag)

fun Dialog.getLong(tag: String, default: Long = 0L) = this.context.getLong(tag, default)

fun Dialog.setFloat(floatValue: Float, tag: String) =
    this.context.setFloat(floatValue, tag)

fun Dialog.getFloat(tag: String, default: Float = 0F) = this.context.getFloat(tag, default)

fun Dialog.setStringSet(strSet: Set<String?>?, tag: String) =
    this.context.setStringSet(strSet, tag)

fun Dialog.getStringSet(
    tag: String,
    prefFileName: String = defaultPrefFileName,
    mode: Int = defaultMode
): Set<String?>? = this.context.getStringSet(tag)
