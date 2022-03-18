package com.zywczas.networkstore.users.networkservice

import com.zywczas.networkstore.users.models.UserNetwork
import com.zywczas.networkstore.utils.Resource

interface UsersNetworkService {

    suspend fun getUsers(): Resource<List<UserNetwork>>

}