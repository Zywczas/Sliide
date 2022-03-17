package com.zywczas.sliide.fragments.userslist.domain

import org.joda.time.DateTime

data class User(
    val name: String = "",
    val email: String = "",
    val dateCreated: DateTime = DateTime.now()
)