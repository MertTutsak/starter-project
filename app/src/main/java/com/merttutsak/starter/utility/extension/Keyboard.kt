package com.merttutsak.starter.utility.extension

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.orhanobut.logger.Logger

/**Examples**/
/**
 * context?.hideKeyboard()
 * context!!.hideKeyboard()
 *
 * if context is null, catch runs.
 */
fun Context?.hideKeyboard(view: View? = null) {
    kotlin.runCatching {
        val inputMethodManager =
            this?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        if (view.isNull() && this is Activity) {
            inputMethodManager?.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                0
            )
        } else {
            inputMethodManager?.hideSoftInputFromWindow(
                view?.applicationWindowToken,
                0
            )
        }
    }

}

/**Examples**/
/**
 * context?.hideKeyboard(view)
 * context!!.hideKeyboard(view)
 *
 * if context is null, catch runs.
 */
fun View?.hideKeyboard() = this?.context.hideKeyboard(this)

/**Examples**/
/**
 * context?.showKeyboard(view)
 * context!!.showKeyboard(view)
 *
 * if context is null, catch runs.
 */

fun View?.showKeyboard() = this?.context?.showKeyboard()

/**Examples**/
/**
 * context?.showKeyboard()
 * context!!.showKeyboard()
 *
 * if context is null, catch runs.
 */
fun Context?.showKeyboard() {
    kotlin.runCatching {
        var imm: InputMethodManager =
            this!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }
}

fun View?.isKeyboardShowing(): Boolean {
    return try {
        val keyboard =
            this?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(this.windowToken, 0)
        keyboard.isActive
    } catch (ex: Exception) {
        Log.e("keyboardHide", "cannot hide keyboard", ex)
        false
    }
}