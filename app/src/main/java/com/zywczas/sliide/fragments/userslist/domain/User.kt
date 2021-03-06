package com.zywczas.sliide.fragments.userslist.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.joda.time.DateTime

@Parcelize
data class User(
    val id: Long = -1,
    val name: String = "",
    val email: String = "",
    val dateCreated: DateTime = DateTime.now()
) : Parcelable