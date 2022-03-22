package com.example.testyourself;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Deck implements Parcelable {
    String name;
    List<String> front = new ArrayList<String>();
    List<String> back = new ArrayList<String>();

    public List<String> getFront()
    {
        return front;
    }

    public List<String> getBack()
    {
        return back;
    }

    protected Deck(Parcel in) {
        name = in.readString();
        front = in.createStringArrayList();
        back = in.createStringArrayList();
    }

    public static final Creator<Deck> CREATOR = new Creator<Deck>() {
        @Override
        public Deck createFromParcel(Parcel in) {
            return new Deck(in);
        }

        @Override
        public Deck[] newArray(int size) {
            return new Deck[size];
        }
    };

    public Deck() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeStringList(this.front);
        parcel.writeStringList(this.back);
    }
}
