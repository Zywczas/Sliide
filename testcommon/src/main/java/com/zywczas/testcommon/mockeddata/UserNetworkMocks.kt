package com.zywczas.testcommon.mockeddata

import com.zywczas.networkstore.users.models.UserNetwork

class UserNetworkMocks {

    private val defaultUser = UserNetwork(
        id = -1L,
        name = "",
        email = ""
    )

    val user1 = UserNetwork(
        id = 1L,
        name = "Piotr",
        email = "piotr@gmail.com"
    )

    val user2 = UserNetwork(
        id = 2L,
        name = "Marcin",
        email = "marcin@gmail.com"
    )

}