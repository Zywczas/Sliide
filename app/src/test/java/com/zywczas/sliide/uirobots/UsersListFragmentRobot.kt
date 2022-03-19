package com.zywczas.sliide.uirobots

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.zywczas.sliide.R
import com.zywczas.sliide.utils.childAtRecyclerPosition
import com.zywczas.sliide.utils.recyclerViewHasSize
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*

@Suppress("HasPlatformType")
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

    fun isToolbarTitle(@StringRes text: Int) = onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText(text))))

    fun usersRecyclerHasItems(number: Int) = onView(withId(R.id.usersList)).check(matches(recyclerViewHasSize(number)))

    fun isFirstMemberDataDisplayed(){
        isMemberNameDisplayed(0, "Piotr")
        isEmailDisplayed(0, "piotr@gmail.com")
        isCreatedOnPlaceholderDisplayed(0)
        isColonDisplayed(0)
        isDateCreatedDisplayed(0)
    }

    private fun isMemberNameDisplayed(position: Int, text: String) = onView(withId(R.id.usersList))
        .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        .check(matches(childAtRecyclerPosition(position, allOf(hasDescendant(allOf(withText(text), withId(R.id.name))), isDisplayed()))))

    private fun isEmailDisplayed(position: Int, text: String) = onView(withId(R.id.usersList))
        .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        .check(matches(childAtRecyclerPosition(position, allOf(hasDescendant(allOf(withText(text), withId(R.id.email))), isDisplayed()))))

    private fun isCreatedOnPlaceholderDisplayed(position: Int) = onView(withId(R.id.usersList))
        .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        .check(matches(childAtRecyclerPosition(position, allOf(hasDescendant(withId(R.id.dateCreatedPlaceholder)), isDisplayed()))))

    private fun isColonDisplayed(position: Int) = onView(withId(R.id.usersList))
        .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        .check(matches(childAtRecyclerPosition(position, allOf(hasDescendant(withId(R.id.colon)), isDisplayed()))))

    private fun isDateCreatedDisplayed(position: Int) = onView(withId(R.id.usersList))
        .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        .check(matches(childAtRecyclerPosition(position, allOf(hasDescendant(withId(R.id.dateCreated)), isDisplayed()))))

    fun isSecondMemberDataDisplayed(){
        isMemberNameDisplayed(1, "Marcin")
        isEmailDisplayed(1, "marcin@gmail.com")
        isCreatedOnPlaceholderDisplayed(1)
        isColonDisplayed(1)
        isDateCreatedDisplayed(1)
    }

}