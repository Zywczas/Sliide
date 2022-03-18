package com.zywczas.networkstore.users.models

import com.google.gson.annotations.SerializedName

internal data class MetaUsers(
    @SerializedName("pagination") val pagination: Pagination = Pagination()
)