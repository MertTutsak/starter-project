package com.merttutsak.starter.data.remote.model.response.animation

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *   Created by mertttutsak on 30.04.2020.
 */

@Parcelize
class AnimationResponse(
    @SerializedName("jsonPath") val jsonPath: String?,
    @SerializedName("repeatMode") val repeatMode: String?
) : Parcelable