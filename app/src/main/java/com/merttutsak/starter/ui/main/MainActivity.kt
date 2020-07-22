package com.merttutsak.starter.ui.main

import android.os.Bundle
import com.merttutsak.starter.R
import com.merttutsak.starter.databinding.ActivityMainBinding
import com.merttutsak.starter.ui.common.base.view.activity.BaseBottomUpActivity
import com.merttutsak.starter.utility.extension.observeNonNull
import javax.inject.Inject

class MainActivity : BaseBottomUpActivity<ActivityMainBinding, MainViewModel>(),
    MainNavigator {

    override val layoutId: Int = R.layout.activity_main

    override lateinit var viewDataBinding: ActivityMainBinding

    @Inject
    override lateinit var viewModel: MainViewModel

    override fun setToolbar() {
        super.setToolbar()
        viewModel.getTitle()
    }

    override fun bindView() {
        viewModel.homeResponseLiveData.observeNonNull(this) {
            viewDataBinding.viewModel = viewModel
        }
    }

    override fun deneme() {
        //TODO deneme
    }
}
