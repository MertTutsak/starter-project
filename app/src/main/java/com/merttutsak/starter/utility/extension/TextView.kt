package com.merttutsak.starter.utility.extension

import android.graphics.Paint
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.merttutsak.starter.R

fun TextView.underline() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

fun TextView.bold() {
    paint.typeface = ResourcesCompat.getFont(context, R.font.demi_bold)
}

fun TextView.regular() {
    paint.typeface = ResourcesCompat.getFont(context, R.font.regular)
}
