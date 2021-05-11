package com.cooskout.spendingcalculator.data

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.cooskout.spendingcalculator.R
import com.cooskout.spendingcalculator.presentation.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun check_chart_visibility() {
        Thread.sleep(500)
        onView(withId(R.id.anyChart)).check(matches(isDisplayed()))

    }
}
