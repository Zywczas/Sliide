package com.zywczas.networkstore.users.networkservice

import com.zywczas.common.extentions.logD
import com.zywczas.networkstore.R
import com.zywczas.networkstore.users.models.UserNetwork
import com.zywczas.networkstore.users.retrofitapi.UsersRetrofitApi
import com.zywczas.networkstore.utils.Resource
import com.zywczas.networkstore.utils.getBearer
import javax.inject.Inject

internal class UsersNetworkServiceImpl @Inject constructor(
    private val usersApi: UsersRetrofitApi
) : UsersNetworkService {

    override suspend fun getUsersLastPage(): Resource<List<UserNetwork>> =
        try { //todo add logic for getting last page
            val response = usersApi.getUsers(bearer = getBearer(), page = 1)
            if (response.code() == 200) {
                val users = response.body()?.users ?: emptyList()
                Resource.Success(users)
            } else {
                Resource.Error(R.string.cannot_download_users)
            }
        } catch (e: Exception) {
            logD(UsersNetworkServiceImpl::class, e)
            Resource.Error(R.string.cannot_download_users)
        }

}