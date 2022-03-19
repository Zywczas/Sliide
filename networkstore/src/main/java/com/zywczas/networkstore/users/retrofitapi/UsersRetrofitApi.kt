package com.zywczas.networkstore.users.retrofitapi

import com.zywczas.networkstore.users.response.UsersResponse
import com.zywczas.networkstore.utils.getBearer
import retrofit2.Response
import retrofit2.http.*

internal interface UsersRetrofitApi {

    @Throws(Exception::class)
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): Response<UsersResponse>

    @Throws(Exception::class)
    @DELETE("users/{userId}")
    suspend fun deleteUser(
        @Header("Authorization") bearer: String = getBearer(),
        @Path("userId") userId: Long
    ): Response<Unit>

    @Throws(Exception::class)
    @POST("users")
    suspend fun createUser(
        @Header("Authorization") bearer: String = getBearer(),
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("gender") gender: String = "male",
        @Query("status") status: String = "active"
    ): Response<Unit>

}