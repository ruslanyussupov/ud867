package com.example.displayjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTv = findViewById(R.id.joke_tv);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_JOKE)) {
            String joke = intent.getStringExtra(EXTRA_JOKE);
            jokeTv.setText(joke);
        }

    }
}
