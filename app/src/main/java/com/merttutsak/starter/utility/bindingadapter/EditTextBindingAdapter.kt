package com.merttutsak.starter.utility.bindingadapter

import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.merttutsak.starter.utility.extension.hideKeyboard
import com.merttutsak.starter.utility.extension.showKeyboard
import com.merttutsak.starter.utility.extension.waitForLayout

object EditTextBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:isOpenKeyboard")
    fun isOpenKeyboard(view: EditText, isOpenKeyboard: Boolean) {
        if (isOpenKeyboard) {
            view.waitForLayout {
                kotlin.runCatching {
                    view.requestFocusFromTouch()
                    view.context.showKeyboard()
                }
            }
        } else {
            kotlin.runCatching {
                view.context.hideKeyboard()
                view.clearFocus()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bind:setTextWatcher")
    fun setTextWatcher(view: EditText, textWatcher: TextWatcher) {
        view.addTextChangedListener(textWatcher)
    }

}