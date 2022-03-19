package com.zywczas.networkstore.users.networkservice

import com.nhaarman.mockitokotlin2.*
import com.zywczas.common.utils.Logger
import com.zywczas.networkstore.R
import com.zywczas.networkstore.mockeddata.UserNetworkMocks
import com.zywczas.networkstore.mockeddata.UsersResponseMocks
import com.zywczas.networkstore.rules.TestCoroutineRule
import com.zywczas.networkstore.users.networkservice.UsersNetworkService
import com.zywczas.networkstore.users.response.UsersResponse
import com.zywczas.networkstore.users.retrofitapi.UsersRetrofitApi
import com.zywczas.networkstore.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class UsersNetworkServiceImplTest {

    @get:Rule
    val coroutineTest = TestCoroutineRule()

    private val usersApi: UsersRetrofitApi = mock()
    private val logger: Logger = mock()
    private val tested = UsersNetworkServiceImpl(usersApi, logger)

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

    @Test
    fun getUsersLastPage_shouldCatchException() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_download_users
        whenever(usersApi.getUsers(any())).thenThrow(Exception::class.java)

        val actual = tested.getUsersLastPage().errorMessage

        verify(usersApi).getUsers(page = 1)
        assertThat(actual).isEqualTo(expected)
    }

}