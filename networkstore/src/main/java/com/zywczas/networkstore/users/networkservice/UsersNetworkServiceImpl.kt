package com.zywczas.networkstore.users.networkservice

import com.zywczas.networkstore.users.models.UserNetwork
import com.zywczas.networkstore.users.retrofitapi.UsersRetrofitApi
import com.zywczas.networkstore.utils.Resource
import javax.inject.Inject

internal class UsersNetworkServiceImpl @Inject constructor(
    private val usersApi: UsersRetrofitApi
) : UsersNetworkService {

    override suspend fun getUsers(): Resource<List<UserNetwork>> {
        TODO("Not yet implemented")
    }

}