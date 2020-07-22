package com.merttutsak.starter.ui.common.base.view.activity;

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.merttutsak.starter.data.remote.model.base.FriendlyMessage
import com.merttutsak.starter.data.remote.service.error.ApiError
import com.merttutsak.starter.ui.common.base.BaseNavigator
import com.merttutsak.starter.ui.common.base.BaseViewModel
import com.merttutsak.starter.ui.common.components.progressdialog.LottieProgressDialog
import com.merttutsak.starter.utility.extension.*
import com.merttutsak.starter.utility.helper.ThemeHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel<*>> :
    DaggerAppCompatActivity(), BaseNavigator{

    abstract val layoutId: Int

    abstract var viewDataBinding: VB

    abstract var viewModel: VM

    private var progressDialog: LottieProgressDialog? = null

    override fun getContext(): Context = this

    @Inject
    lateinit var themeHelper: ThemeHelper

    open fun onCreateActivity(savedInstanceState: Bundle?) {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewModel.setNav(this)
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

        setToolbar()
        bindView()

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
        friendlyMessage?.let {//TODO: dialog utils ile duzelt
            this.showToast(friendlyMessage.title)
        }
    }

    /* override fun attachBaseContext(newBase: Context) {
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
}