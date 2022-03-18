package com.zywczas.networkstore.users.retrofitapi

import com.zywczas.networkstore.users.response.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

internal interface UsersRetrofitApi {

    @GET("users")
    suspend fun getUsers(
        @Header("Authorization") bearer: String,
        @Query("page") page: Int
    ): Response<UsersResponse>

}