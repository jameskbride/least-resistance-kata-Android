package com.jameskbride.leastresistance;

import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "src/main/AndroidManifest.xml", sdk = 18)
public class PathFinderFragmentTest {

    private PathFinderFragment pathFinderFragment;
    private LeastResistanceActivity activity;

    @Before
    public void setUp() {
        this.pathFinderFragment = PathFinderFragment.newInstance();;
        this.activity = Robolectric.buildActivity(LeastResistanceActivity.class).create().start().visible().get();
        this.activity.getSupportFragmentManager().beginTransaction().add(pathFinderFragment, null).commit();
    }

    @Test
    public void whenTheFragmentViewIsCreatedThenTheLayoutIsBound() {
        Button findPathButton = (Button)pathFinderFragment.getView().findViewById(R.id.find_path_button);
        assertNotNull(findPathButton);
    }
}
