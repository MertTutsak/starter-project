package com.merttutsak.starter.ui.common.base

import android.content.Context
import com.merttutsak.starter.data.remote.model.base.FriendlyMessage
import com.merttutsak.starter.data.remote.service.error.ApiError

interface BaseNavigator {
    fun showLoading()
    fun hideLoading()

    fun showFail(apiError: ApiError, friendlyMessage: FriendlyMessage?)
}