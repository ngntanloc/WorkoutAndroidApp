package com.example.workoutapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Training implements Parcelable {


    protected Training(Parcel in) {
        id = in.readInt();
        name = in.readString();
        shortDesc = in.readString();
        longDesc = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(shortDesc);
        dest.writeString(longDesc);
        dest.writeString(imageUrl);
    }

    private int id;
    private String name;
    private String shortDesc, longDesc;
    private String imageUrl;

    public Training(int id, String name, String shortDesc, String longDesc, String imageUrl) {
        this.id = id;
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
