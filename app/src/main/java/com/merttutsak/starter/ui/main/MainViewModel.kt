package com.merttutsak.starter.ui.main

import androidx.lifecycle.MutableLiveData
import com.merttutsak.starter.data.remote.model.response.HomeResponse
import com.merttutsak.starter.data.remote.service.resource.Resource
import com.merttutsak.starter.data.remote.service.resource.Status
import com.merttutsak.starter.ui.common.base.BaseViewModel
import com.merttutsak.starter.utility.extension.plusAssign
import com.merttutsak.starter.utility.provider.SchedulerProvider
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject internal constructor() : BaseViewModel<MainNavigator>() {

    var homeResponseLiveData = MutableLiveData<Resource<HomeResponse>>()

    fun getTitle() {
        navigator.showLoading()
        disposable.plusAssign(
            dataManagerImp.apiHelperImp.getHome(appLanguageProvider.getAppLanguage().code())
                .compose(SchedulerProvider.ioToMainObservableScheduler())
                .subscribe {
                    when (it.status) {
                        Status.SUCCESS -> {

                        }
                        Status.ERROR -> {

                        }
                    }
                    navigator.hideLoading()
                    homeResponseLiveData.value = it
                }
        )
    }
}