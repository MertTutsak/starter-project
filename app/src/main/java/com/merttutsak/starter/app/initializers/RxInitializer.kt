package com.merttutsak.starter.app.initializers

import android.app.Application
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import javax.inject.Inject

class RxInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        RxJavaPlugins.setErrorHandler {
            Logger.e(it.localizedMessage?:"")
        }
    }
}