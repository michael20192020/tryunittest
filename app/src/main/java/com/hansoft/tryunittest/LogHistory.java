package com.hansoft.tryunittest;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class LogHistory implements Parcelable{

    private final List<Pair<String, Long>> mData = new ArrayList<>();
    private static String name;
    private static Long number;

    public LogHistory()
    {

    }

    private LogHistory(Parcel in) {

        int length = in.readInt();
        String[] texts = new String[length];
        long[] timestamps = new long[length];
        in.readStringArray(texts);
        in.readLongArray(timestamps);
        if (texts.length != timestamps.length) {
            throw new IllegalStateException("Error reading from saved state.");
        }
        mData.clear();
        for (int i = 0; i < texts.length; i++) {
            Pair<String, Long> pair = new Pair<>(texts[i], timestamps[i]);
            mData.add(pair);
        }
    }


    public void addEntry(String text, long timestamp) {
        mData.add(new Pair<String, Long>(text, timestamp));
    }


    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        String[] texts = new String[mData.size()];
        long[] timestamps = new long[mData.size()];
        for (int i = 0; i < mData.size(); i++) {
            texts[i] = mData.get(i).first;
            timestamps[i] = mData.get(i).second;
        }
        out.writeInt(texts.length);
        out.writeStringArray(texts);
        out.writeLongArray(timestamps);
    }

    public static final Parcelable.Creator<LogHistory> CREATOR
            = new Parcelable.Creator<LogHistory>() {
        public LogHistory createFromParcel(Parcel in) {
            return new LogHistory(in);
        }

        public LogHistory[] newArray(int size) {
              return new LogHistory[size];
        }
    };

    public List<Pair<String, Long>> getData() {
        return new ArrayList<>(mData);
    }



}
