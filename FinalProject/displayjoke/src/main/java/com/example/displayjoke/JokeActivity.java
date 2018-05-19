package com.example.displayjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";
    public static final String BUNDLE_JOKE = "joke";

    private Joke mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (savedInstanceState == null) {

            Intent intent = getIntent();

            if (intent.hasExtra(EXTRA_JOKE)) {
                mJoke = intent.getParcelableExtra(EXTRA_JOKE);
                updateUi();
            }

        } else {

            mJoke = savedInstanceState.getParcelable(BUNDLE_JOKE);
            updateUi();

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BUNDLE_JOKE, mJoke);
        super.onSaveInstanceState(outState);
    }

    private void updateUi() {

        if (mJoke == null) {
            return;
        }

        TextView categoryTv = findViewById(R.id.category_tv);
        TextView jokeTv = findViewById(R.id.joke_tv);
        RatingBar ratingBar = findViewById(R.id.rating);

        categoryTv.setText(mJoke.getCategory());
        jokeTv.setText(mJoke.getBody());
        ratingBar.setRating(mJoke.getRating());

    }

}
