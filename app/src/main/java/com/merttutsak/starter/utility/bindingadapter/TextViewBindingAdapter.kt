package com.merttutsak.starter.utility.bindingadapter

import android.graphics.Typeface
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.merttutsak.starter.R
import com.merttutsak.starter.utility.extension.isNotNull
import com.merttutsak.starter.utility.extension.notNull
import com.merttutsak.starter.utility.extension.setOnDebouncedClickListener
import com.merttutsak.starter.utility.extension.underline
import java.lang.Exception

object TextViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:setUnderline")
    fun setUnderline(view: TextView, isUnderline: Boolean) {
        view.underline()
    }

    @JvmStatic
    @BindingAdapter("bind:setTextId")
    fun setTextId(view: TextView, resourceId: Int) {
        kotlin.runCatching {
            if(resourceId.isNotNull() && resourceId != 0){
                view.setText(resourceId)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bind:typeface")
    fun setTypeface(v: TextView, style: Typeface?) {
        v.typeface = style
    }

    @JvmStatic
    @BindingAdapter("bind:setActive")
    fun setActive(view: TextView?, isActive: Boolean?) {
        isActive?.let {
            if (isActive) {
                view?.setBackgroundResource(R.drawable.button_enable)
                view?.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
            } else {
                view?.setBackgroundResource(R.drawable.button_disable)
                view?.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
            }
            view?.isClickable = isActive
            view?.isEnabled = isActive
        }
    }

    @JvmStatic
    @BindingAdapter("bind:onSafeClick")
    fun onSafeClick(view: TextView?, onClick: () -> Unit) {
        view?.setOnDebouncedClickListener {
            onClick()
        }
    }

}