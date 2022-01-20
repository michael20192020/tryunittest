package com.hansoft.tryunittest;

import android.os.Parcel;
import android.os.Parcelable;

public class Author implements Parcelable {
    private int age;
    private String name;

    public Author(int newage,String newname)
    {
        age = newage;
        name = newname;

    }

    private Author(Parcel in) {

        age = in.readInt();
        name = in.readString();

    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeInt(age);
        out.writeString(name);
    }

    public static final Parcelable.Creator<Author> CREATOR
            = new Parcelable.Creator<Author>() {
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    public int getAge()
    {
        return age;
    }

    public String getName()
    {
        return name;
    }
}
