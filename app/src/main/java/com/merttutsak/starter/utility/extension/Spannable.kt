package com.merttutsak.starter.utility.extension

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import androidx.core.content.res.ResourcesCompat
import com.merttutsak.starter.utility.helper.typeFace.TypefaceSpan

fun SpannableString.setTextSize(
    size: Float,
    start: Int = 0,
    end: Int = this.length
) {
    this.setSpan(
        RelativeSizeSpan(size),
        start,
        end,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

fun SpannableString.setTextColor(
    cxt: Context,
    colorId: Int,
    start: Int = 0,
    end: Int = this.length
) {
    this.setSpan(
        ForegroundColorSpan(cxt.resColor(colorId)),
        start,
        end,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

fun SpannableString.setFontFamily(
    cxt: Context,
    fontId: Int,
    start: Int = 0,
    end: Int = this.length
) {
    this.setSpan(
        TypefaceSpan(
            ResourcesCompat.getFont(cxt, fontId)
        ),
        start,
        end,
        Spannable.SPAN_EXCLUSIVE_INCLUSIVE
    )
}