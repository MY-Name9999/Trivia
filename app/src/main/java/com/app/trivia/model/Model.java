package com.app.trivia.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.List;

public class Model implements Parcelable
{
    private String name;
    private String cricketer;
    private String colors;

    public Model(String name, String cricketer, String colors) {
        this.name = name;
        this.cricketer = cricketer;
        this.colors = colors;
    }

    public Model(){}

    protected Model(Parcel in) {
        name = in.readString();
        cricketer = in.readString();
        colors = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCricketer() {
        return cricketer;
    }

    public void setCricketer(String cricketer) {
        this.cricketer = cricketer;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getColors() {
        return String.join(", ", colors);
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(cricketer);
        dest.writeString(colors);
    }
}
