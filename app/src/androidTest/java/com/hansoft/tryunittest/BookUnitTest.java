package com.hansoft.tryunittest;

import android.nfc.TagLostException;
import android.os.Parcel;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BookUnitTest {

    private int age;
    private String authorname;
    private int id;
    private String name;
    private boolean isCell;
    private ArrayList<String> tags;
    private Author myAuthor;
    private Book myBook;

    @Before
    public void createBook() {
        age = 30;
        authorname = "Tom";
        id = 1;
        name = "android studio";
        isCell = true;
        tags = new ArrayList<String>();
        tags.add("how to learn android studio");
        myAuthor = new Author(age,authorname);
        myBook = new Book();
        myBook.addBook(id,name,isCell,tags,myAuthor);

    }

    @Test
    public void book_ParcelableWriteRead() {


        // Write the data.
        Parcel parcel = Parcel.obtain();
        myBook.writeToParcel(parcel, myBook.describeContents());
        parcel.setDataPosition(0);
        Book createdFromParcel = Book.CREATOR.createFromParcel(parcel);
        assertThat(createdFromParcel.getid()).isEqualTo(id);
        assertThat(createdFromParcel.getName()).isEqualTo(name);
        assertThat(createdFromParcel.getiscell()).isEqualTo(1);
        assertThat(createdFromParcel.getAuthor()).isInstanceOf(Author.class);

    }
}
