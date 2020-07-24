package com.merttutsak.starter.data.remote.service.resource

import androidx.annotation.NonNull
import com.merttutsak.starter.data.remote.model.base.BaseResponse
import com.merttutsak.starter.data.remote.service.error.ApiError

class Resource<T> constructor(
    val status: Status,
    val data: T? = null,
    val error: ApiError? = null
) {

    companion object {

        fun <T> success(@NonNull data: T): Resource<T> = Resource(Status.SUCCESS, data)

        fun <T> error(throwable: Throwable): Resource<T> =
            Resource(status = Status.ERROR, error = ApiError.handleException(throwable))

        fun <T> error(data: T?): Resource<T> =
            Resource(status = Status.ERROR, data = data, error = ApiError.handleException(if(data is BaseResponse) data.status else null))

        fun <T> loading(): Resource<T> = Resource(Status.LOADING)

    }

}