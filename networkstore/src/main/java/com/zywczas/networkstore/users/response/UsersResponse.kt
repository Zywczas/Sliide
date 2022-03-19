package com.zywczas.networkstore.users.response

import com.google.gson.annotations.SerializedName
import com.zywczas.networkstore.users.models.MetaUsers
import com.zywczas.networkstore.users.models.UserNetwork

data class UsersResponse(
    @SerializedName("meta") val meta: MetaUsers = MetaUsers(),
    @SerializedName("data") val users: List<UserNetwork> = emptyList()
)