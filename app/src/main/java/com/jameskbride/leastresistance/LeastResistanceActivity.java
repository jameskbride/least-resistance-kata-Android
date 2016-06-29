package com.jameskbride.leastresistance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LeastResistanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PathFinderFragment(), PathFinderFragment.class.getName()).commit();
    }
}
