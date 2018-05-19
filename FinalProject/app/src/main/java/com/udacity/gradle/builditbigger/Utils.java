package com.udacity.gradle.builditbigger;


import android.util.Log;

import com.example.displayjoke.Joke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Utils {

    private static final String LOG_TAG = Utils.class.getSimpleName();
    private static final String JSON_BODY = "body";
    private static final String JSON_ID = "id";
    private static final String JSON_CATEGORY = "category";
    private static final String JSON_RATING = "rating";

    public static ArrayList<Joke> getJokes(String json) {

        ArrayList<Joke> jokes = null;

        if (json != null && !json.isEmpty()) {

            try {

                JSONArray jsonArray = new JSONArray(json);
                int length = jsonArray.length();

                if (length != 0) {

                    jokes = new ArrayList<>(length);

                    for (int i = 0; i < length; i++) {

                        JSONObject jokeJson = jsonArray.getJSONObject(i);

                        int id = jokeJson.getInt(JSON_ID);
                        String body = jokeJson.getString(JSON_BODY);
                        String category = jokeJson.getString(JSON_CATEGORY);
                        float rating = (float) jokeJson.getDouble(JSON_RATING);

                        Joke joke = new Joke(id, category, body, rating);

                        jokes.add(joke);

                    }

                }


            } catch (JSONException e) {
                Log.e(LOG_TAG, "Can't parse JSON.", e);
            }

        }

        return jokes;

    }

}
