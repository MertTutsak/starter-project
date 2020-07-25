package com.merttutsak.starter.ui.common.base.view.activity;

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.merttutsak.starter.data.remote.model.base.FriendlyMessage
import com.merttutsak.starter.data.remote.service.error.ApiError
import com.merttutsak.starter.ui.common.base.BaseNavigator
import com.merttutsak.starter.ui.common.base.BaseViewModel
import com.merttutsak.starter.ui.common.components.progressdialog.LottieProgressDialog
import com.merttutsak.starter.utility.delegate.AutoClearedActivityValue
import com.merttutsak.starter.utility.extension.*
import com.merttutsak.starter.utility.helper.ThemeHelper
import com.merttutsak.starter.utility.wrapper.AppContextWrapper
import com.orhanobut.logger.Logger
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel<*>> :
    DaggerAppCompatActivity(), BaseNavigator {

    open val layoutId: Int by AutoClearedActivityValue()

    open var viewDataBinding: VB by AutoClearedActivityValue()

    open var viewModel: VM by AutoClearedActivityValue()

    private var progressDialog: LottieProgressDialog? = null

    @Inject
    lateinit var themeHelper: ThemeHelper

    open fun onCreateActivity(savedInstanceState: Bundle?) {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        if (!this.javaClass.genericInterfaces.isNullOrEmpty()) {
            viewModel.setNav(this)
        }
    }

    open fun bindView() {
        viewDataBinding.lifecycleOwner = this
    }

    open fun setToolbar() {
        this.initStatusBar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        themeHelper.init(this)
        onCreateActivity(savedInstanceState)

        bindView()
        setToolbar()
    }

    //TODO viewmodel inject hata
    /*override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            AppContextWrapper.wrap(
                newBase,
                viewModel.appLanguageProvider.getAppLanguage()
            )
        )
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        super.applyOverrideConfiguration(
            AppContextWrapper.wrap(
                applicationContext,
                viewModel.appLanguageProvider.getAppLanguage()
            ).resources.configuration
        )
    }*/

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        } else {
            //TODO show dialog if user wants to exit app
            finish()
        }
    }

    override fun hideLoading() {
        progressDialog.notNull { it.cancel() }
    }

    override fun showLoading() {
        hideLoading()
        progressDialog.isNull {
            progressDialog = LottieProgressDialog(this)
        }

        progressDialog?.show()
    }

    override fun showFail(apiError: ApiError, friendlyMessage: FriendlyMessage?) {
        friendlyMessage?.let {
            //TODO: show dialog by DialogUtils
            this.showToast(friendlyMessage.title)
        }
    }
}