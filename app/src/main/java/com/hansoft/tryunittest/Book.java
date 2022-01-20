package com.hansoft.tryunittest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Book implements Parcelable {
    public int id;
    public String name;
    public boolean isCell;
    public ArrayList<String> tags = new ArrayList<>();
    public Author author;

    public ArrayList<Author> authors = new ArrayList<>();

    public Book() {
    }

    public void addBook(int newid, String newname,boolean newisCell,ArrayList<String> newtags,Author newauthor)
    {
        id = newid;
        name = newname;
        isCell = newisCell;
        tags = newtags;
        author = newauthor;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        name = in.readString();
        isCell = in.readByte() != 0;

        tags = in.createStringArrayList();

        // 读取对象需要提供一个类加载器去读取,因为写入的时候写入了类的相关信息
        author = in.readParcelable(Author.class.getClassLoader());

        //读取集合也分为两类,对应写入的两类
        //这一类需要用相应的类加载器去获取
        //in.readList(authors, Author.class.getClassLoader()); // 对应writeList

        //这一类需要使用类的CREATOR去获取
        in.readTypedList(authors, Author.CREATOR); //对应writeTypeList
        //authors = in.createTypedArrayList(Author.CREATOR);//对应writeTypeList

        //这里获取类加载器主要有几种方式
       // getClass().getClassLoader();
       // Thread.currentThread().getContextClassLoader();
       // Author.class.getClassLoader();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (isCell ? 1 : 0));
        dest.writeStringList(tags);


        dest.writeParcelable(author, flags);

        // 序列化一个对象的集合有两种方式,以下两种方式都可以

        //这些方法们把类的信息和数据都写入Parcel，以使将来能使用合适的类装载器重新构造类的实例.所以效率不高
       // dest.writeList(authors);

        //这些方法不会写入类的信息，取而代之的是：读取时必须能知道数据属于哪个类并传入正确的Parcelable.Creator来创建对象
        // 而不是直接构造新对象。（更加高效的读写单个Parcelable对象的方法是：
        // 直接调用Parcelable.writeToParcel()和Parcelable.Creator.createFromParcel()）
        dest.writeTypedList(authors);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    public int getid()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getiscell()
    {
        return isCell ? 1 : 0;
    }

    public Author getAuthor()
    {
        return author;
    }
}
