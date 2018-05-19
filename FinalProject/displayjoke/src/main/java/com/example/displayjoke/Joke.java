package com.example.displayjoke;

import android.os.Parcel;
import android.os.Parcelable;

public class Joke implements Parcelable {

    private int id;
    private String category;
    private String body;
    private float rating;

    public Joke(int id, String category, String body, float rating) {
        this.id = id;
        this.category = category;
        this.body = body;
        this.rating = rating;
    }

    protected Joke(Parcel in) {
        id = in.readInt();
        category = in.readString();
        body = in.readString();
        rating = in.readFloat();
    }

    public static final Creator<Joke> CREATOR = new Creator<Joke>() {
        @Override
        public Joke createFromParcel(Parcel in) {
            return new Joke(in);
        }

        @Override
        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getBody() {
        return body;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(category);
        dest.writeString(body);
        dest.writeFloat(rating);
    }
}
