package com.zywczas.networkstore.users.networkservice

import com.nhaarman.mockitokotlin2.*
import com.zywczas.networkstore.mockeddata.UserNetworkMocks
import com.zywczas.networkstore.mockeddata.UsersResponseMocks
import com.zywczas.networkstore.rules.TestCoroutineRule
import com.zywczas.networkstore.users.networkservice.UsersNetworkService
import com.zywczas.networkstore.users.response.UsersResponse
import com.zywczas.networkstore.users.retrofitapi.UsersRetrofitApi
import com.zywczas.networkstore.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class UsersNetworkServiceImplTest {

    @get:Rule
    val coroutineTest = TestCoroutineRule()

    private val usersApi: UsersRetrofitApi = mock()
    private val tested = UsersNetworkServiceImpl(usersApi)

    private val usersResponseMocks = UsersResponseMocks()
    private val usersNetworkMocks = UserNetworkMocks()

    @Test
    fun getUsersLastPage_shouldGetUsersLastPage() = coroutineTest.runBlockingTest {
        val expected = listOf(usersNetworkMocks.user1, usersNetworkMocks.user2)
        whenever(usersApi.getUsers(any())).thenReturn(Response.success(200, usersResponseMocks.response1))

        val actual = tested.getUsersLastPage().data

        verify(usersApi).getUsers(page = 1)
        verify(usersApi).getUsers(page = 23)
        assertThat(actual).isEqualTo(expected)
    }

}