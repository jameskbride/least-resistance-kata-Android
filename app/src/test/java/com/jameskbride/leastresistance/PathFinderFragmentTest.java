package com.jameskbride.leastresistance;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
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

    @Test
    public void givenAValidMapHasBeenEnteredWhenAPathIsSearchedForThenItIsDisplayed() {
        EditText editText = (EditText)pathFinderFragment.getView().findViewById(R.id.map_text);
        editText.setText("1 2 3 4 5");

        pathFinderFragment.getView().findViewById(R.id.find_path_button).performClick();

        TextView pathText = (TextView)pathFinderFragment.getView().findViewById(R.id.path_text);

        assertThat(pathText.getText().toString(), equalTo("Yes\n15\n1 1 1 1 1"));
    }

    @Test
    public void givenAnInvalidMapHasBeenEnteredWhenAPathIsSearchedForThenAToastIsDisplayedToPromptForAValidToast() {
        EditText editText = (EditText)pathFinderFragment.getView().findViewById(R.id.map_text);
        editText.setText("1 2 3 4");

        pathFinderFragment.getView().findViewById(R.id.find_path_button).performClick();

        TextView pathText = (TextView)pathFinderFragment.getView().findViewById(R.id.path_text);

        assertThat(pathText.getText().toString(), equalTo(pathFinderFragment.getString(R.string.create_valid_map_error_text)));
    }
}
