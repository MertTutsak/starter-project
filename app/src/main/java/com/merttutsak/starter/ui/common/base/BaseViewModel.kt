package com.merttutsak.starter.ui.common.base

import androidx.lifecycle.ViewModel
import com.merttutsak.starter.data.manager.DataManagerImp
import com.merttutsak.starter.data.remote.model.base.BaseResponse
import com.merttutsak.starter.utility.delegate.OnceCreatableDelegate
import com.merttutsak.starter.utility.provider.AppLanguageProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseViewModel<N : BaseNavigator> : ViewModel() {

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var dataManagerImp: DataManagerImp

    @Inject
    lateinit var appLanguageProvider: AppLanguageProvider

    private var mNavigator: WeakReference<N>? by OnceCreatableDelegate()

    var navigator: N
        get() {
            return mNavigator?.get()!!
        }
    set(value) {
        this.mNavigator = WeakReference(value)
    }

    fun setNav(baseNavigator: BaseNavigator) {
        navigator = baseNavigator as N
    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }

    fun checkResponse(baseResponse: BaseResponse): Boolean {
        return true
    }
}