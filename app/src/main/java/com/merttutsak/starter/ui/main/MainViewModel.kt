package com.merttutsak.starter.ui.main

import androidx.lifecycle.MutableLiveData
import com.merttutsak.starter.R
import com.merttutsak.starter.data.remote.model.response.home.HomeResponse
import com.merttutsak.starter.data.remote.service.resource.Resource
import com.merttutsak.starter.data.remote.service.resource.Status
import com.merttutsak.starter.ui.common.base.BaseViewModel
import com.merttutsak.starter.utility.extension.plusAssign
import com.merttutsak.starter.utility.provider.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject internal constructor() : BaseViewModel<MainNavigator>() {

    var homeResponseLiveData = MutableLiveData<Resource<HomeResponse>>()
    var resourceAppName = MutableLiveData<Int>()

    fun getTitle() {
        navigator.showLoading()
        disposable.plusAssign(
            dataManagerImp.apiHelperImp.getHome(appLanguageProvider.getAppLanguage().code())
                .compose(SchedulerProvider.ioToMainObservableScheduler())
                .subscribe {
                    resourceAppName.value = (R.string.app_name)

                    when (it.status) {
                        Status.SUCCESS -> {
                            homeResponseLiveData.value = it
                        }
                    }

                    navigator.hideLoading()
                }
        )
    }
}