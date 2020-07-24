package com.merttutsak.starter.data.remote.model.base

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.merttutsak.starter.data.remote.model.base.FriendlyMessage
import com.merttutsak.starter.data.remote.model.base.ProcessStatus
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseResponse(
    @SerializedName("processStatus")
    @Expose
    var status: ProcessStatus? = null,
    @SerializedName("serverTimeUTCInSeconds")
    @Expose
    var serverTimeUTCInSeconds: Long? = null ?: -1L,
    @SerializedName("friendlyMessage")
    @Expose
    var friendlyMessage: FriendlyMessage? = null
) : Parcelable{

    val isSuccess: Boolean
        get() = (status != null && status!!.isSuccess)

}