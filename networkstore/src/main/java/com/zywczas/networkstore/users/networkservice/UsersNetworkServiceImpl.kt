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
            val response = usersApi.getUsers(page = 1)
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

    override suspend fun deleteUser(id: Long): Resource<Int> =
        try {
            val response = usersApi.deleteUser(userId = id)
            if (response.code() == 204) {
                Resource.Success(R.string.deleted_user)
            } else {
                Resource.Error(R.string.cannot_delete_user)
            }
        } catch (e: Exception) {
            logD(UsersNetworkServiceImpl::class, e)
            Resource.Error(R.string.cannot_delete_user)
        }

    override suspend fun createUser(name: String, email: String): Resource<Int> =
        try {
            val response = usersApi.createUser(name = name, email = email)
            if (response.code() == 201) {
                Resource.Success(R.string.added_user)
            } else {
                Resource.Error(R.string.cannot_add_user)
            }
        } catch (e: Exception) {
            logD(UsersNetworkServiceImpl::class, e)
            Resource.Error(R.string.cannot_add_user)
        }

}