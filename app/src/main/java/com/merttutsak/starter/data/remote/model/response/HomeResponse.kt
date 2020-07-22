package com.merttutsak.starter.data.remote.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
 class HomeResponse(@SerializedName("title") val title: String) : Parcelable, BaseResponse()