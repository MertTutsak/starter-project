package com.merttutsak.starter.data.remote.service.api

import com.merttutsak.starter.data.remote.model.response.home.HomeResponse
import com.merttutsak.starter.data.remote.service.resource.Resource
import com.merttutsak.starter.utility.provider.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject


class ApiHelperImp @Inject constructor(
    private val apiHelper: ApiHelper
) {

    fun getHome(lang: String): Observable<Resource<HomeResponse>> {
        return apiHelper.getHome(lang)
            .map {
                if (it.isSuccess) {
                    Resource.success(it)
                } else {
                    Resource.error(it)
                }
            }
            .retryWhen { obsrvable ->
                obsrvable.flatMap {
                    Observable.just<Resource<HomeResponse>>(
                        Resource.error(
                            it
                        )
                    )
                }
            }
            .compose(SchedulerProvider.ioToMainObservableScheduler())

    }

//    private fun checkBaseResponse(baseActivity: BaseActivity, baseResponse: BaseResponse) {
//        DialogUtils.showFriendlyMessage(
//            baseActivity,
//            baseResponse.friendlyMessage,
//            baseResponse.processStatus
//        )
//    }
}