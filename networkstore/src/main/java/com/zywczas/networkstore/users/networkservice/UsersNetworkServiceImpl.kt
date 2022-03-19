package com.zywczas.networkstore.users.networkservice

import com.zywczas.common.utils.Logger
import com.zywczas.networkstore.R
import com.zywczas.networkstore.users.models.UserNetwork
import com.zywczas.networkstore.users.retrofitapi.UsersRetrofitApi
import com.zywczas.networkstore.utils.Resource
import javax.inject.Inject

internal class UsersNetworkServiceImpl @Inject constructor(
    private val usersApi: UsersRetrofitApi,
    private val logger: Logger
) : UsersNetworkService {

    override suspend fun getUsersLastPage(): Resource<List<UserNetwork>> =
        try {
            val firstResponse = usersApi.getUsers(page = 1)
            if (firstResponse.code() == 200) {
                val lastPage = firstResponse.body()?.meta?.pagination?.pages ?: 0
                val lastPageResponse = usersApi.getUsers(page = lastPage)
                if (lastPageResponse.code() == 200) {
                    val users = lastPageResponse.body()?.users ?: emptyList()
                    Resource.Success(users)
                } else {
                    Resource.Error(R.string.cannot_download_users)
                }
            } else {
                Resource.Error(R.string.cannot_download_users)
            }
        } catch (e: Exception) {
            logger.logD(UsersNetworkServiceImpl::class, e)
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
            logger.logD(UsersNetworkServiceImpl::class, e)
            Resource.Error(R.string.cannot_delete_user)
        }

    override suspend fun createUser(name: String, email: String): Resource<Int> =
        try {
            val response = usersApi.createUser(name = name, email = email)
            if (response.code() == 201) {
                Resource.Success(R.string.created_user)
            } else {
                Resource.Error(R.string.cannot_create_user)
            }
        } catch (e: Exception) {
            logger.logD(UsersNetworkServiceImpl::class, e)
            Resource.Error(R.string.cannot_create_user)
        }

}