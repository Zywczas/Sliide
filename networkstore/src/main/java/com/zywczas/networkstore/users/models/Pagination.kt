package com.zywczas.networkstore.users.models

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("pages") val pages: Int = 0
)