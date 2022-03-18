package com.zywczas.sliide.fragments.userslist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.zywczas.networkstore.utils.Resource
import com.zywczas.sliide.fragments.userslist.domain.UsersListRepository
import com.zywczas.sliide.mockeddata.UserMocks
import com.zywczas.sliide.rules.TestCoroutineRule
import com.zywczas.sliide.utils.LiveDataTestUtil
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

}