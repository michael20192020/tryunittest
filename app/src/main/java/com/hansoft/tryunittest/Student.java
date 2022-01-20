package com.hansoft.tryunittest;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

public class Student implements Parcelable {
    private int age;
    private String name;
    private boolean isBoy;

    public Student()
    {

    }

    private Student(Parcel in) {

        age = in.readInt();
        name = in.readString();
        isBoy = in.readInt() == 1;

    }

    public void addStudent(int newage,String newname, boolean newisboy) {
        age = newage;
        name = newname;
        isBoy = newisboy;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeInt(age);
        out.writeString(name);
        out.writeInt(isBoy ? 1 : 0);
    }

    public static final Parcelable.Creator<Student> CREATOR
            = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
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

    public int getisBoy()
    {
        int result = isBoy ? 1 : 0;
        return result;
    }

}
