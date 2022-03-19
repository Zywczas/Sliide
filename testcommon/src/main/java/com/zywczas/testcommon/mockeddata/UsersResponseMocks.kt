package com.zywczas.testcommon.mockeddata

import com.zywczas.networkstore.users.models.MetaUsers
import com.zywczas.networkstore.users.models.Pagination
import com.zywczas.networkstore.users.response.UsersResponse
import com.zywczas.testcommon.mockeddata.UserNetworkMocks

class UsersResponseMocks {

    private val usersNetworkMocks = UserNetworkMocks()

    private val defaultResponse = UsersResponse(
        meta = MetaUsers(
            pagination = Pagination(pages = 0)
        ),
        users = emptyList()
    )

    val response1 = UsersResponse(
        meta = MetaUsers(
            pagination = Pagination(pages = 23)
        ),
        users = listOf(usersNetworkMocks.user1, usersNetworkMocks.user2)
    )

}