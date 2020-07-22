package com.merttutsak.starter.data.remote.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class FriendlyMessage(
    @SerializedName("displayType")
    @Expose
    var displayType: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("cancelable")
    @Expose
    var cancelable: Boolean? = null,
    @SerializedName("buttonSet")
    @Expose
    var buttonSet: List<ButtonSet> = ArrayList()
) : Parcelable