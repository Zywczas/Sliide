package com.zywczas.sliide.fragments.userslist.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import com.zywczas.networkstore.utils.Resource
import com.zywczas.sliide.R
import com.zywczas.sliide.di.factories.UniversalViewModelFactory
import com.zywczas.sliide.fragments.userslist.domain.UsersListRepository
import com.zywczas.sliide.uirobots.UsersListFragmentRobot
import com.zywczas.testcommon.mockeddata.UserMocks
import com.zywczas.testcommon.rules.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(
    // required by Espresso to access final members on androidx.loader.content.ModernAsyncTask
    instrumentedPackages = ["androidx.loader.content"]
)
class UsersListFragmentTest {

    @get:Rule
    val coroutineTest = TestCoroutineRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: UsersListRepository = mock()

    private val uiRobot = UsersListFragmentRobot()
    private val userMock = UserMocks()

    private fun launchUsersListFragment() : FragmentScenario<UsersListFragment> {
        val viewModelFactory = spy(UniversalViewModelFactory(emptyMap()))
        val viewModel = UsersListViewModel(TestCoroutineRule.testDispatcher, repository)

        doReturn(viewModel).`when`(viewModelFactory).create(UsersListViewModel::class.java)

        return launchFragmentInContainer(themeResId = R.style.Theme_Sliide) {
            UsersListFragment(viewModelFactory)
        }
    }

    @Before
    fun setUp() = coroutineTest.runBlockingTest {
        whenever(repository.getUsersLastPage()).thenReturn(Resource.Success(listOf(userMock.user1, userMock.user2)))
    }

    @Test
    fun startFragment_shouldGetLayoutDisplayed() = coroutineTest.runBlockingTest {
        launchUsersListFragment()

        uiRobot.isLayoutDisplayed()
    }

}