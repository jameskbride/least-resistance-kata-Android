package com.jameskbride.leastresistance;

import android.support.v4.app.Fragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "src/main/AndroidManifest.xml", sdk = 18)
public class LeastResistanceActivityTest {

    @Test
    public void whenTheActivityIsCreatedThenThePathFinderFragmentIsLoaded() {
        LeastResistanceActivity activity =
                Robolectric.buildActivity(LeastResistanceActivity.class).create().visible().get();

        Fragment pathFinderFragment = activity.getSupportFragmentManager()
                .findFragmentByTag(PathFinderFragment.class.getName());
        assertThat(pathFinderFragment.isAdded(), equalTo(true));
    }
}