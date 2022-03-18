package com.zywczas.sliide.fragments.userslist.domain

import org.joda.time.DateTime

data class User(
    val id: Long = -1,
    val name: String = "",
    val email: String = "",
    val dateCreated: DateTime = DateTime.now()
)