package com.jameskbride.leastresistance;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class LeastResistanceActivityInstrumentationTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(LeastResistanceActivity.class);

    @Test
    public void givenAMapWithAMinimalValidPathWhenThePathIsCalculatedThenItShouldReturnThePath() {
        onView(withId(R.id.map_text)).perform(typeText("1 2 3 4 5"), closeSoftKeyboard());

        onView(withId(R.id.find_path_button)).perform(click());

        onView(withText("Yes\n15\n1 1 1 1 1")).check(matches(isDisplayed()));
    }

    @Test
    public void givenAMapWithAComplexValidPathWhenThePathIsCalculatedThenItShouldReturnThePath() {
        onView(withId(R.id.map_text)).perform(typeText(
                "3 4 1 2 8 6" + "\n" +
                "6 1 8 2 7 4" + "\n" +
                "5 9 3 9 9 5" + "\n" +
                "8 4 1 3 2 6" + "\n" +
                "3 7 2 8 6 4"), closeSoftKeyboard());

        onView(withId(R.id.find_path_button)).perform(click());

        onView(withText("Yes\n16\n1 2 3 4 4 5")).check(matches(isDisplayed()));
    }

    @Test
    public void givenAMapWithAValidPathWhichOverflowsWhenThePathIsCalculatedThenItShouldReturnThePath() {
        onView(withId(R.id.map_text)).perform(typeText(
                "3 4 1 2 8 6" + "\n" +
                "6 1 8 2 7 4" + "\n" +
                "5 9 3 9 9 5" + "\n" +
                "8 4 1 3 2 6" + "\n" +
                "3 7 2 1 2 3"), closeSoftKeyboard());

        onView(withId(R.id.find_path_button)).perform(click());

        onView(withText("Yes\n11\n1 2 1 5 5 5")).check(matches(isDisplayed()));
    }

    @Test
    public void givenAMapWithNoValidPathWhenThePathIsCalculatedThenItShouldNotReturnThePath() {
        onView(withId(R.id.map_text)).perform(typeText(
                "19 10 19 10 19" + "\n" +
                "21 23 20 19 12" + "\n" +
                "20 12 20 11 10"), closeSoftKeyboard());

        onView(withId(R.id.find_path_button)).perform(click());

        onView(withText("No\n48\n1 1 1")).check(matches(isDisplayed()));
    }
}
