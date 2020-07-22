package com.merttutsak.starter.ui.common.base.view.activity

import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.merttutsak.starter.ui.common.base.BaseNavigator
import com.merttutsak.starter.ui.common.base.BaseViewModel
import com.merttutsak.starter.utility.transition.Transition


abstract class BaseFadeInOutActivity<VB: ViewDataBinding, VM: BaseViewModel<*>> : BaseActivity<VB, VM>() {

    private var transition: Transition = Transition.TransitionFadeInOut()

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    private fun overridePendingTransitionEnter() {
        overridePendingTransition(transition.enterAnim, transition.exitAnim)
    }

    private fun overridePendingTransitionExit() {
        overridePendingTransition(transition.enterAnimBack, transition.exitAnimBack)
    }

    fun setTransition(transition: Transition) {
        this.transition = transition
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransitionExit()
    }
}