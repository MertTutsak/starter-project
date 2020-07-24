package com.merttutsak.starter.data.remote.model.response.animation

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
class AnimationResponse(
    @SerializedName("jsonPath") val jsonPath: String?,
    @SerializedName("repeatMode") val repeatMode: String?
) : Parcelable