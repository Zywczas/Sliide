package com.zywczas.networkstore.users.models

import com.google.gson.annotations.SerializedName

data class UserNetwork(
    @SerializedName("id") val id: Long = -1,
    @SerializedName("name") val name: String = "",
    @SerializedName("email") val email: String = ""
)