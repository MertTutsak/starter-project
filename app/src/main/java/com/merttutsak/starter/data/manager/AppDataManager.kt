package com.merttutsak.starter.data.manager

import com.merttutsak.starter.data.local.db.AppDatabase
import com.merttutsak.starter.data.local.db.model.entity.Data
import com.merttutsak.starter.data.local.preferences.SharedPrefHelper
import com.merttutsak.starter.data.remote.service.api.ApiHelperImp
import com.merttutsak.starter.utility.Constants
import com.merttutsak.starter.utility.extension.isNull
import com.merttutsak.starter.utility.helper.analytics.AnalyticsHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class AppDataManager @Inject internal constructor(
    val compositeDisposable: CompositeDisposable,
    val apiHelperImp: ApiHelperImp,
    val db: AppDatabase,
    val sharedPrefHelper: SharedPrefHelper
) : DataManager {
    @Inject
    override lateinit var analyticsHelper: AnalyticsHelper

    fun initData(data: Data): Data? {
        if (!db.dataDao().getDatas().isNull() && !db.dataDao().getDataByName(data.data)
                .isNullOrEmpty()
        ) {
            db.dataDao().updateData(data)
        } else if (!db.dataDao().getDatas().isNullOrEmpty()) {
            db.dataDao().deleteAllDatas()
            db.dataDao().insertData(data)
        } else {
            db.dataDao().insertData(data)
        }
        return db.dataDao().getDatas().firstOrNull()
    }

    override fun isUserLoggedIn(): Boolean {
        return this.sharedPrefHelper.getUserLoggedInMode() != Constants.App.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.ordinal
    }

    override fun performUserLogout() {
        sharedPrefHelper.let {
            it.setUserLoggedInMode(Constants.App.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT)
        }
    }
}