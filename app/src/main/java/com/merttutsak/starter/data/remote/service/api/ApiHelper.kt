package com.merttutsak.starter.data.remote.service.api

import android.content.Context
import com.merttutsak.starter.BuildConfig
import com.merttutsak.starter.data.remote.model.response.HomeResponse
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiHelper {

    companion object {

        fun createRetrofit(context: Context): ApiHelper {

            val builder = OkHttpClient.Builder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.addInterceptor(LoggingInterceptor())
            builder.addInterceptor(ChuckInterceptor(context))
            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiHelper::class.java)

        }
    }


    @GET("/v2/5d3e09a7320000fa1b6e55c4")
    fun getHome(@Query("culture") lang: String): Observable<HomeResponse>


}