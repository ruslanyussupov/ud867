package com.ruslaniusupov.android.createjavalibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ruslaniusupov.android.jokelib.JokeSmith;
import com.ruslaniusupov.android.jokewizardlib.JokeWizard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mJokeTv = findViewById(R.id.joke_tv);
        TextView mJokeWizardTv = findViewById(R.id.joke_wizard_tv);

        mJokeTv.setText(JokeSmith.getJoke());
        mJokeWizardTv.setText(JokeWizard.getJoke());

    }
}
