package com.zywczas.sliide.fragments.userslist.domain

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.networkstore.users.models.UserNetwork
import com.zywczas.networkstore.users.networkservice.UsersNetworkService
import com.zywczas.networkstore.utils.Resource
import javax.inject.Inject

class UsersListRepositoryImpl @Inject constructor(
    private val usersService: UsersNetworkService,
    private val dateTime: DateTimeProvider
) : UsersListRepository {

    override suspend fun getUsersLastPage(): Resource<List<User>> =
        when (val networkResource = usersService.getUsersLastPage()) {
            is Resource.Success -> Resource.Success(networkResource.data?.map { it.toDomain() } ?: emptyList())
            is Resource.Error -> Resource.Error(networkResource.errorMessage)
        }

    private fun UserNetwork.toDomain() = User(
        id = id,
        name = name,
        email = email,
        dateCreated = dateTime.now()
    )

    override suspend fun deleteUser(id: Long): Resource<Int> = usersService.deleteUser(id)

    override suspend fun createUser(name: String, email: String): Resource<Int> = usersService.createUser(name, email)

}