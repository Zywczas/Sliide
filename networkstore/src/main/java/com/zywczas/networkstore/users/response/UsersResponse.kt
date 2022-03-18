package com.zywczas.networkstore.users.response

import com.zywczas.networkstore.users.models.UserNetwork

internal data class UsersResponse(
    val users: List<UserNetwork> = emptyList()
)