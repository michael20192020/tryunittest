package com.hansoft.tryunittest;

import android.os.Parcel;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class StudentUnitTest {
    private String name = "jack";
    private int age = 12;
    private boolean isBoy = false;
    private Student myStudent;

    @Before
    public void createStudent() {
        myStudent = new Student();
    }

    @Test
    public void student_ParcelableWriteRead() {
        isBoy = true;
        myStudent.addStudent(age,name,isBoy);
        // Write the data.
        Parcel parcel = Parcel.obtain();
        myStudent.writeToParcel(parcel, myStudent.describeContents());

        parcel.setDataPosition(0);
        Student createdFromParcel = Student.CREATOR.createFromParcel(parcel);
        assertThat(createdFromParcel.getAge()).isEqualTo(age);
        assertThat(createdFromParcel.getName()).isEqualTo(name);
        assertThat(createdFromParcel.getisBoy()).isEqualTo(1);
    }
}
