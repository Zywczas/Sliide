package com.zywczas.sliide.fragments.userslist.domain

import com.zywczas.networkstore.users.models.UserNetwork
import com.zywczas.networkstore.users.networkservice.UsersNetworkService
import com.zywczas.networkstore.utils.Resource
import javax.inject.Inject

class UsersListRepositoryImpl @Inject constructor(
    private val usersService: UsersNetworkService
) : UsersListRepository {

    override suspend fun getUsersLastPage(): Resource<List<User>> {
        val networkResource = usersService.getUsersLastPage()
        return if (networkResource is Resource.Success) {
            val resource = networkResource.data?.map { it.toDomain() } ?: emptyList()
            Resource.Success(resource)
        } else {
            Resource.Error(networkResource.message)
        }
    }

    private fun UserNetwork.toDomain() = User(
        id = id,
        name = name,
        email = email
    )

}