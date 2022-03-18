package com.zywczas.networkstore.users.networkservice

import com.zywczas.networkstore.users.models.UserNetwork
import com.zywczas.networkstore.utils.Resource

interface UsersNetworkService {

    suspend fun getUsersLastPage(): Resource<List<UserNetwork>>
    suspend fun deleteUser(id: Long): Resource<Int>
    suspend fun createUser(name: String, email: String): Resource<Int>

}