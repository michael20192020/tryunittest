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
public class AuthorUnitTest {
    private String name = "tom";
    private int age = 34;

    private Author myAuthor;

    @Before
    public void createAuthor() {
        myAuthor = new Author(age,name);
    }


    @Test
    public void author_ParcelableWriteRead() {


        // Write the data.
        Parcel parcel = Parcel.obtain();
        myAuthor.writeToParcel(parcel, myAuthor.describeContents());

        parcel.setDataPosition(0);
        Author createdFromParcel = Author.CREATOR.createFromParcel(parcel);
        assertThat(createdFromParcel.getAge()).isEqualTo(age);
        assertThat(createdFromParcel.getName()).isEqualTo(name);

    }

}
