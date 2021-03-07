package me.ezzattharwat.breakingbad.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import me.ezzattharwat.breakingbad.DataStatus
import me.ezzattharwat.breakingbad.R
import me.ezzattharwat.breakingbad.TestUtil.dataStatus
import me.ezzattharwat.breakingbad.util.Errors.NETWORK_ERROR
import me.ezzattharwat.breakingbad.util.EspressoIdlingResource
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, false, false)
    private var mIdlingResource: IdlingResource? = null

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun displayRecipesList() {
        dataStatus = DataStatus.Success
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.charactersRV)).check(matches(isDisplayed()))
        onView(withId(R.id.charactersPB)).check(matches(Matchers.not(isDisplayed())))
    }

    @Test
    fun displayNoData(){
        dataStatus = DataStatus.EmptyResponse
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.charactersRV)).check(matches(not(isDisplayed())))
        onView(withId(R.id.charactersPB)).check(matches(Matchers.not(isDisplayed())))
        onView(withId(R.id.emptyTV)).check(matches(isDisplayed()))
    }

    @Test
    fun displaySnackBarIfFail(){
        dataStatus = DataStatus.Fail
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.charactersRV)).check(matches(not(isDisplayed())))
        onView(withId(R.id.charactersPB)).check(matches(Matchers.not(isDisplayed())))
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(NETWORK_ERROR)))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }

}