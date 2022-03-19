package com.zywczas.sliide.fragments.userslist.domain

import com.nhaarman.mockitokotlin2.*
import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.networkstore.users.networkservice.UsersNetworkService
import com.zywczas.networkstore.utils.Resource
import com.zywczas.sliide.R
import com.zywczas.sliide.mockeddata.UserMocks
import com.zywczas.sliide.mockeddata.UserNetworkMocks
import com.zywczas.sliide.rules.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersListRepositoryImplTest {

    @get:Rule
    val coroutineTest = TestCoroutineRule()

    private val usersService: UsersNetworkService = mock()
    private val dateTime: DateTimeProvider = mock()
    private val tested = UsersListRepositoryImpl(usersService, dateTime)

    private val userMock = UserMocks()
    private val userNetworkMock = UserNetworkMocks()

    @Test
    fun getUsersLastPage_shouldGetUsers() = coroutineTest.runBlockingTest {
        val expected = listOf(userMock.user1, userMock.user2)
        whenever(usersService.getUsersLastPage()).thenReturn(Resource.Success(listOf(userNetworkMock.user1, userNetworkMock.user2)))
        whenever(dateTime.now()).thenReturn(DateTime(0))

        val actual = tested.getUsersLastPage().data

        verify(usersService).getUsersLastPage()
        verify(dateTime, times(2)).now()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getUsersLastPage_shouldGetError() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_download_users
        whenever(usersService.getUsersLastPage()).thenReturn(Resource.Error(R.string.cannot_download_users))

        val actual = tested.getUsersLastPage().errorMessage

        verify(dateTime, times(0)).now()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun deleteUser_shouldDelete() = coroutineTest.runBlockingTest {
        val expected = R.string.deleted_user
        whenever(usersService.deleteUser(any())).thenReturn(Resource.Success(R.string.deleted_user))

        val actual = tested.deleteUser(2L).data

        verify(usersService).deleteUser(2L)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun createUser_shouldCreate() = coroutineTest.runBlockingTest {
        val expected = R.string.created_user
        whenever(usersService.createUser(any(), any())).thenReturn(Resource.Success(R.string.created_user))

        val actual = tested.createUser("myName", "myEmail").data

        verify(usersService).createUser("myName", "myEmail")
        assertThat(actual).isEqualTo(expected)
    }

}