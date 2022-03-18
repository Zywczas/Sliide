package com.zywczas.networkstore.users.retrofitapi

import com.zywczas.networkstore.users.response.UsersResponse
import retrofit2.Response
import retrofit2.http.*

internal interface UsersRetrofitApi {

//    @GET("users")
//    suspend fun getUsers(
//        @Header("Authorization") bearer: String,
//        @Query("page") page: Int
//    ): Response<UsersResponse>

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): Response<UsersResponse>

    @DELETE("users/{userId}")
    suspend fun deleteUser(
        @Header("Authorization") bearer: String,
        @Path("userId") userId: Long
    ): Response<UsersResponse>

}