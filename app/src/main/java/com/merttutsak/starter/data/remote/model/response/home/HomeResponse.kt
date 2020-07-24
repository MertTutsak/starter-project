package com.merttutsak.starter.data.remote.model.response.home

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.merttutsak.starter.data.remote.model.base.BaseResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
 class HomeResponse(@SerializedName("title") val title: String) : Parcelable, BaseResponse()