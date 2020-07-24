package com.merttutsak.starter.utility.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.merttutsak.starter.utility.extension.getStatusBarHeight

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("bind:invisibleIf")
    fun changeInVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("bind:layoutWidth")
    fun setLayoutWidth(layout: View, dimen: Float) {
        val layoutParams = layout.layoutParams
        layoutParams.width = dimen.toInt()
        layout.layoutParams = layoutParams
    }

    @JvmStatic
    @BindingAdapter("bind:initStatusBarHeight")
    fun initStatusBarHeight(view: View, hasHeight: Boolean) {
        if (hasHeight) {
            view.setPadding(
                view.paddingLeft,
                view.paddingTop + view.context.getStatusBarHeight(),
                view.paddingRight,
                view.bottom
            )
        }
    }

}