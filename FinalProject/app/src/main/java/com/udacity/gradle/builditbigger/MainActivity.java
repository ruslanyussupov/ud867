package com.udacity.gradle.builditbigger;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.displayjoke.Joke;
import com.example.displayjoke.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String BUNDLE_JOKES = "jokes";

    private static MyApi mMyApiService;
    private List<Joke> mJokes;

    @BindView(R.id.loading_pb)ProgressBar mLoadingPb;
    @BindView(R.id.content)Group mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {

            new EndpointsAsyncTask().execute();

        } else {

            mJokes = savedInstanceState.getParcelableArrayList(BUNDLE_JOKES);

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(BUNDLE_JOKES, (ArrayList<? extends Parcelable>) mJokes);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.tell_joke_btn)
    public void tellJoke(View view) {

        if (mJokes != null && mJokes.size() != 0) {

            int length = mJokes.size();

            Random random = new Random();
            int index = random.nextInt(length-1);
            Joke joke = mJokes.get(index);

            Intent launchJokeActivity = new Intent(this, JokeActivity.class);
            launchJokeActivity.putExtra(JokeActivity.EXTRA_JOKE, joke);
            startActivity(launchJokeActivity);

        } else {
            Toast.makeText(this, getString(R.string.no_joke_msg), Toast.LENGTH_SHORT).show();
        }

    }

    private class EndpointsAsyncTask extends AsyncTask<Void, Void, List<Joke>> {

        @Override
        protected List<Joke> doInBackground(Void... params) {

            showProgressBar();

            if (mMyApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                mMyApiService = builder.build();

            }

            String json = null;
            List<Joke> result = null;

            try {
                json =  mMyApiService.getJokesJson().execute().getJson();
                Log.d(LOG_TAG, json);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Can't get data from API Service. " + e.getMessage());
            }

            if (!TextUtils.isEmpty(json)) {
                result = Utils.getJokes(json);
            }

            return result;

        }

        @Override
        protected void onPostExecute(List<Joke> result) {

            hideProgressBar();

            mJokes = result;

        }

    }

    private void showProgressBar() {
        mContent.setVisibility(View.GONE);
        mLoadingPb.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mLoadingPb.setVisibility(View.GONE);
        mContent.setVisibility(View.VISIBLE);
    }

}
