package com.merttutsak.starter.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.merttutsak.starter.BuildConfig
import com.merttutsak.starter.data.remote.service.api.ApiHelper
import com.merttutsak.starter.data.remote.service.api.HttpLoggingInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit): ApiHelper = retrofit.create(ApiHelper::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckInterceptor: ChuckInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
            builder.addInterceptor(chuckInterceptor)
        }

        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideChuckInterceptor(context: Context): ChuckInterceptor = ChuckInterceptor(context)

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()

}