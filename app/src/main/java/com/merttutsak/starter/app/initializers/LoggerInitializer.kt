package com.merttutsak.starter.app.initializers

import android.app.Application
import com.merttutsak.starter.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import javax.inject.Inject

class LoggerInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}
