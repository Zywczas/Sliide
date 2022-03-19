package com.zywczas.sliide.fragments.userslist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.zywczas.networkstore.utils.Resource
import com.zywczas.sliide.R
import com.zywczas.sliide.fragments.userslist.domain.UsersListRepository
import com.zywczas.sliide.utils.LiveDataTestUtil
import com.zywczas.testcommon.mockeddata.UserMocks
import com.zywczas.testcommon.rules.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersListViewModelTest {

    @get:Rule
    val coroutineTest = TestCoroutineRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: UsersListRepository = mock()
    private val tested = UsersListViewModel(TestCoroutineRule.testDispatcher, repository)

    private val userMock = UserMocks()

    @Before
    fun setUp() = coroutineTest.runBlockingTest {
        whenever(repository.getUsersLastPage()).thenReturn(Resource.Success(listOf(userMock.user1, userMock.user2)))
    }

    @Test
    fun getUsers_shouldGetUsers() = coroutineTest.runBlockingTest {
        val expected = listOf(userMock.user1, userMock.user2)

        tested.getUsers()
        val actual = LiveDataTestUtil.getValue(tested.usersList)

        verify(repository).getUsersLastPage()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getUsers_shouldGetError() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_download_users
        whenever(repository.getUsersLastPage()).thenReturn(Resource.Error(R.string.cannot_download_users))

        tested.getUsers()
        val actual = LiveDataTestUtil.getValue(tested.message)

        verify(repository).getUsersLastPage()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun getUsers_shouldNotDisplayProgressbar() = coroutineTest.runBlockingTest {
        tested.getUsers()
        val actual = LiveDataTestUtil.getValue(tested.isProgressBarVisible)

        assertThat(actual).isFalse
    }

    @Test
    fun deleteUser_shouldDelete() = coroutineTest.runBlockingTest {
        val expected = R.string.deleted_user
        whenever(repository.deleteUser(any())).thenReturn(Resource.Success(R.string.deleted_user))

        tested.deleteUser(3L)
        val actual = LiveDataTestUtil.getValue(tested.message)

        verify(repository).deleteUser(3L)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun deleteUser_shouldGetError() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_delete_user
        whenever(repository.deleteUser(any())).thenReturn(Resource.Error(R.string.cannot_delete_user))

        tested.deleteUser(4L)
        val actual = LiveDataTestUtil.getValue(tested.message)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun deleteUser_shouldUpdateUsers() = coroutineTest.runBlockingTest {
        whenever(repository.deleteUser(any())).thenReturn(Resource.Error(R.string.cannot_delete_user))

        tested.deleteUser(5L)

        verify(repository).getUsersLastPage()
    }

    @Test
    fun addUser_shouldBeAdded() = coroutineTest.runBlockingTest {
        val expected = R.string.created_user
        whenever(repository.createUser(any(), any())).thenReturn(Resource.Success(R.string.created_user))

        tested.createUser("myName", "myEmail")
        val actual = LiveDataTestUtil.getValue(tested.message)

        verify(repository).createUser("myName", "myEmail")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun addUser_shouldGetError() = coroutineTest.runBlockingTest {
        val expected = R.string.cannot_create_user
        whenever(repository.createUser(any(), any())).thenReturn(Resource.Error(R.string.cannot_create_user))

        tested.createUser("myName", "myEmail")
        val actual = LiveDataTestUtil.getValue(tested.message)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun addUser_shouldUpdateUsers() = coroutineTest.runBlockingTest {
        whenever(repository.createUser(any(), any())).thenReturn(Resource.Success(R.string.created_user))

        tested.createUser("myName", "myEmail")

        verify(repository).getUsersLastPage()
    }

}