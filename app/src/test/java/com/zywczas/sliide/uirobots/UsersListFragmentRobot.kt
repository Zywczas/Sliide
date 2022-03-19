package com.zywczas.sliide.uirobots

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.zywczas.sliide.BaseApp
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

    fun clickOnCreateUser() = onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())

    fun isCreateUserDialogDisplayed(){
        onView(withId(R.id.name)).inRoot(isDialog()).check(matches(allOf(withHint(R.string.name), isDisplayed())))
        onView(withId(R.id.email)).inRoot(isDialog()).check(matches(allOf(withHint(R.string.email), isDisplayed())))
        onView(withId(R.id.confirm)).inRoot(isDialog()).check(matches(allOf(withText(R.string.add), isDisplayed())))
        onView(withId(R.id.cancel)).inRoot(isDialog()).check(matches(allOf(withText(R.string.cancel), isDisplayed())))
    }

    fun longTapOnUser(position: Int) = onView(withId(R.id.usersList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, longClick()))

    fun isConfirmationDialogDisplayed() {
        onView(withId(R.id.title)).inRoot(isDialog()).check(matches(allOf(withText(R.string.delete_user), isDisplayed())))
        onView(withId(R.id.message)).inRoot(isDialog()).check(matches(allOf(withText(getString(R.string.are_you_sure_delete_user, "Piotr")), isDisplayed())))
        onView(withId(R.id.confirm)).inRoot(isDialog()).check(matches(allOf(withText(R.string.yes), isDisplayed())))
        onView(withId(R.id.cancel)).inRoot(isDialog()).check(matches(allOf(withText(R.string.cancel), isDisplayed())))
    }

    private fun getString(@StringRes msg: Int, arg: String) = ApplicationProvider.getApplicationContext<BaseApp>().applicationContext.getString(msg, arg)

    fun createUser(name: String, email: String) {
        clickOnCreateUser()
        onView(withId(R.id.name)).inRoot(isDialog()).perform(typeText(name))
        onView(withId(R.id.email)).inRoot(isDialog()).perform(typeText(email))
        onView(withId(R.id.confirm)).inRoot(isDialog()).perform(click())
    }

    fun isSnackbarDisplayed(@StringRes text: Int) = onView(allOf(withId(com.google.android.material.R.id.snackbar_text), withText(text))).check(matches(isDisplayed()))

    fun deleteUser(position: Int) {
        onView(withId(R.id.usersList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, longClick()))
        onView(withId(R.id.confirm)).inRoot(isDialog()).perform(click())
    }

}