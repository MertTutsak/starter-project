package com.merttutsak.starter.data.remote.service.api

import com.merttutsak.starter.data.remote.model.response.home.HomeResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {

    @GET("/v2/5d3e09a7320000fa1b6e55c4")
    fun getHome(@Query("culture") lang: String): Observable<HomeResponse>


}