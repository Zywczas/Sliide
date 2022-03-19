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
class UsersNetworkServiceImplTest {

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

    @Test
    fun getUsersLastPage_whenFirstResponseIsError_shouldGetError() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_download_users
        whenever(usersApi.getUsers(any())).thenReturn(Response.success(222, usersResponseMocks.response1))

        val actual = tested.getUsersLastPage().errorMessage

        verify(usersApi).getUsers(page = 1)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getUsersLastPage_whenLastPageResponseIsError_shouldGetError() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_download_users
        whenever(usersApi.getUsers(any()))
            .thenReturn(Response.success(200, usersResponseMocks.response1))
            .thenReturn(Response.success(222, usersResponseMocks.response1))

        val actual = tested.getUsersLastPage().errorMessage

        verify(usersApi).getUsers(page = 1)
        verify(usersApi).getUsers(page = 23)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun deleteUser_shouldDelete() = coroutineTest.runBlockingTest {
        val expected = R.string.deleted_user
        whenever(usersApi.deleteUser(any(), any())).thenReturn(Response.success(204, Unit))

        val actual = tested.deleteUser(2L).data

        verify(usersApi).deleteUser(bearer = any(), userId = eq(2L))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun deleteUser_shouldCatchException() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_delete_user
        whenever(usersApi.deleteUser(any(), any())).thenThrow(Exception::class.java)

        val actual = tested.deleteUser(2L).errorMessage

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun deleteUser_whenErrorOccurred_shouldGetError() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_delete_user
        whenever(usersApi.deleteUser(any(), any())).thenReturn(Response.success(222, Unit))

        val actual = tested.deleteUser(2L).errorMessage

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun createUser_shouldCreate() = coroutineTest.runBlockingTest {
        val expected = R.string.created_user
        whenever(usersApi.createUser(any(), any(), any(), any(), any())).thenReturn(Response.success(201, Unit))

        val actual = tested.createUser("myName", "myEmail").data

        verify(usersApi).createUser(
            bearer = any(),
            name = eq("myName"),
            email = eq("myEmail"),
            gender = eq("male"),
            status = eq("active")
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun createUser_shouldCatchException() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_create_user
        whenever(usersApi.createUser(any(), any(), any(), any(), any())).thenThrow(Exception::class.java)

        val actual = tested.createUser("myName", "myEmail").errorMessage

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun createUser_whenErrorOccurred_shouldGetError() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_create_user
        whenever(usersApi.createUser(any(), any(), any(), any(), any())).thenReturn(Response.success(222, Unit))

        val actual = tested.createUser("myName", "myEmail").errorMessage

        assertThat(actual).isEqualTo(expected)
    }

}