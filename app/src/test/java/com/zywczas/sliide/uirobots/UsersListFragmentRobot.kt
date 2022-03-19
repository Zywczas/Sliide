package com.zywczas.sliide.uirobots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.zywczas.sliide.R
import org.hamcrest.Matchers.not

class UsersListFragmentRobot {

    fun isLayoutDisplayed(){
        isAppbarDisplayed()
        isToolbarDisplayed()
        isProgressBarNotDisplayed()
        isRecyclerViewDisplayed()
        isFabDisplayed()
    }

    private fun isAppbarDisplayed() = onView(withId(R.id.appbar)).check(matches(isDisplayed()))

    private fun isToolbarDisplayed() = onView(withId(R.id.toolbar)).check(matches(isDisplayed()))

    private fun isProgressBarNotDisplayed() = onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))

    private fun isRecyclerViewDisplayed() = onView(withId(R.id.usersList)).check(matches(isDisplayed()))

    private fun isFabDisplayed() = onView(withId(R.id.fab)).check(matches(isDisplayed()))

}