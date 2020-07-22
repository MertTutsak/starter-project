package com.merttutsak.starter.data.remote.model.base

import android.os.Parcel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class ButtonSet(
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @Expose
    var text: String? = null,
    @SerializedName("action")
    @Expose
    var action: String? = null,
    @SerializedName("actionParameter")
    @Expose
    var actionParameter: String? = null
) : Parcelable