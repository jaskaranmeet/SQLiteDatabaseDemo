package com.example.hp.sqlitedatabasedemo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SingleRow implements Parcelable {
private String id,name,password;



    public SingleRow(String id, String name, String password) {

        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    protected SingleRow(Parcel in) {
        this.id = in.readString();
       this.name = in.readString();
        this.password = in.readString();
    }

    public static final Creator<SingleRow> CREATOR = new Creator<SingleRow>() {
        @Override
        public SingleRow createFromParcel(Parcel in) {
            return new SingleRow(in);
        }

        @Override
        public SingleRow[] newArray(int size) {
            return new SingleRow[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(password);
    }
}
