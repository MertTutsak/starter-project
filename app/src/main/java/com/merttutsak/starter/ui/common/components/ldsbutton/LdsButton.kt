package com.merttutsak.starter.ui.common.components.ldsbutton

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.merttutsak.starter.R

class LdsButton : AppCompatButton {
    enum class State {
        DISABLED,
        ENABLE
    }

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }


    private fun init(context: Context?, attrs: AttributeSet?) {
        setStatus(State.ENABLE)
    }

    fun setStatus(state: State) {
        if (state == State.ENABLE) {
            isClickable = true
            background = ContextCompat.getDrawable(context, R.drawable.lds_button_enable)
        } else {
            isClickable = false
            background = ContextCompat.getDrawable(context, R.drawable.lds_button_disable)
        }
    }
}