package com.merttutsak.starter.data.remote.model.base

import android.os.Parcel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class ProcessStatus(
    @SerializedName("code")
    @Expose
    var code: Int? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null
) : Parcelable{

    val isSuccess: Boolean
        get() = (code != null && code == 2000)

}