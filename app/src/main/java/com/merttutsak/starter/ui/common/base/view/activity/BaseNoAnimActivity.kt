package com.merttutsak.starter.ui.common.base.view.activity

import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.merttutsak.starter.ui.common.base.BaseNavigator
import com.merttutsak.starter.ui.common.base.BaseViewModel

abstract class BaseNoAnimActivity<VB: ViewDataBinding, VM: BaseViewModel<*>> : BaseActivity<VB, VM>() {

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    private fun overridePendingTransitionEnter() {
        overridePendingTransition(0, 0)
    }

    private fun overridePendingTransitionExit() {
        overridePendingTransition(0, 0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransitionExit()
    }
}