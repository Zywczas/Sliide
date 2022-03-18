package com.zywczas.sliide.fragments.userslist.domain

import com.zywczas.networkstore.utils.Resource

interface UsersListRepository {

    suspend fun getUsersLastPage(): Resource<List<User>>
    suspend fun deleteUser(id: Long): Resource<Int>
    suspend fun createUser(name: String, email: String): Resource<Int>

}