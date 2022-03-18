package com.zywczas.networkstore.di.modules

import com.zywczas.networkstore.users.retrofitapi.UsersRetrofitApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    private val goRestBaseUrl = "https://gorest.co.in/public/v1/"

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(goRestBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    internal fun provideUsersRetrofitApi(retrofit: Retrofit): UsersRetrofitApi = retrofit.create(UsersRetrofitApi::class.java)

}