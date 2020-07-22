package com.merttutsak.starter.data.remote.service.error

import android.os.Parcelable
import com.google.gson.JsonParseException
import com.merttutsak.starter.data.remote.model.base.ProcessStatus
import com.merttutsak.starter.utility.extension.isNull
import com.orhanobut.logger.Logger
import kotlinx.android.parcel.Parcelize
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

@Parcelize
class ApiError(
    var status: ErrorStatus = ErrorStatus.UNKNOWN_ERROR,
    var message: String = "Request unsuccessful"
) : Parcelable {

    companion object {

        fun handleException(status: ProcessStatus?): ApiError {
            val error = ApiError()

            if (status != null) {
                error.status = ErrorStatus.fromCode(status.code ?: -1)
                error.message = status.description ?: "Unknown Exception"
            } else {
                error.status = ErrorStatus.UNKNOWN_ERROR
                error.message = "Unknown Exception"
            }

            return error
        }


        fun handleException(apiCode: Int? = -1, message: String? = null): ApiError {
            val error = ApiError()

            error.status = ErrorStatus.fromCode(apiCode ?: -1)
            error.message = message ?: "Unknown Exception"

            return error
        }

        fun handleException(e: Throwable): ApiError {
            val error = ApiError()

            error.status = when (e) {
                is SocketTimeoutException -> ErrorStatus.NETWORK_ERROR
                is ConnectException -> ErrorStatus.NETWORK_ERROR
                is JsonParseException -> ErrorStatus.NETWORK_ERROR
                is ApiException -> ErrorStatus.SERVER_ERROR
                is JsonParseException -> ErrorStatus.SERVER_ERROR
                is ParseException -> ErrorStatus.SERVER_ERROR
                is JSONException -> ErrorStatus.SERVER_ERROR
                is UnknownHostException -> ErrorStatus.NETWORK_ERROR
                is IllegalArgumentException -> ErrorStatus.SERVER_ERROR
                else -> {
                    ErrorStatus.UNKNOWN_ERROR
                }
            }

            error.message = when (e) {
                is SocketTimeoutException -> {
                    "timeout"
                }
                is ConnectException -> {
                    "No Connect"
                }
                is JsonParseException -> {
                    "No Connect"
                }
                is ApiException -> {
                    "Parse Exception"
                }
                is ParseException -> {
                    "Parse Exception"
                }
                is JSONException -> {
                    "Parse Exception"
                }
                is UnknownHostException -> {
                    "UnknownHostException"
                }
                is IllegalArgumentException -> {
                    "IllegalArgumentException"
                }
                else -> {
                    "Unknown Exception"
                }
            }

            Logger.e(
                "TAG",
                "${error.status.name}: ${error.message} \n Throwable error : ${e.localizedMessage ?: ""}"
            )

            return error
        }
    }
}