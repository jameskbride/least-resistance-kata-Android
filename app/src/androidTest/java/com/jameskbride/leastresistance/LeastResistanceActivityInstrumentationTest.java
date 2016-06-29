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
}
