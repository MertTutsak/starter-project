package com.merttutsak.starter.ui.common.components.viewpager.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

open class BaseViewPager : ViewPager {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
    }

    abstract class ViewHolder(var view: View) {
        abstract fun bind(baseViewModel: BaseViewModel, position: Int)

        abstract class BaseViewModel
    }

}