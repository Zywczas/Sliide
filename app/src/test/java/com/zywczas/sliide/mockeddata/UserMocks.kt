package com.zywczas.sliide.mockeddata

import com.zywczas.sliide.fragments.userslist.domain.User
import org.joda.time.DateTime

class UserMocks {

    private val defaultUser = User(
        id = -1L,
        name = "",
        email = "",
        dateCreated = DateTime(0)
    )

    val user1 = User(
        id = 1L,
        name = "Piotr",
        email = "piotr@gmail.com",
        dateCreated = DateTime(0)
    )

    val user2 = User(
        id = 2L,
        name = "Marcin",
        email = "marcin@gmail.com",
        dateCreated = DateTime(0)
    )

}