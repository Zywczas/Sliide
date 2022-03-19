package com.zywczas.networkstore.mockeddata

import com.zywczas.networkstore.users.models.UserNetwork
//todo try to place both in common place, now I have 2 copies in 2 places
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