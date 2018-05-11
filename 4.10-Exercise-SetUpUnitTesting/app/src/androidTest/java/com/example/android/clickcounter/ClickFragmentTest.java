package com.example.android.clickcounter;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ClickFragmentTest {

    @Rule
    public ActivityTestRule<ClickActivity> mActivityRule =
            new ActivityTestRule<>(ClickActivity.class);

    @Test
    public void displayClickCount() {

        Espresso.onView(ViewMatchers.withId(R.id.click_count_text_view))
                .check(ViewAssertions.matches(ViewMatchers.withText("0")));

        Espresso.onView(ViewMatchers.withId(R.id.click_button))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.click_count_text_view))
                .check(ViewAssertions.matches(ViewMatchers.withText("1")));

    }
}