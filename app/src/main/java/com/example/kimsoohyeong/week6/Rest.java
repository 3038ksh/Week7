package com.example.kimsoohyeong.week6;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by KimSooHyeong on 2017. 4. 6..
 */

public class Rest implements Parcelable {
    private String name = "";
    private String callNum = "";
    private String[] menu = new String[3];
    private String url = "";
    private String date = "";
    private int isCheck = 0;
    private int num = -1;

    public Rest(String name, String callNum, String menu[], String url, String date, int num) {
        this.name = name;
        this.callNum = callNum;
        this.menu = menu;
        this.url = url;
        this.date = date;
        this.num = num;
    }

    protected Rest(Parcel in) {
        name = in.readString();
        callNum = in.readString();
        menu = in.createStringArray();
        url = in.readString();
        date = in.readString();
        num = in.readInt();
        isCheck = in.readInt();
    }

    public static final Creator<Rest> CREATOR = new Creator<Rest>() {
        @Override
        public Rest createFromParcel(Parcel in) {
            return new Rest(in);
        }

        @Override
        public Rest[] newArray(int size) {
            return new Rest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(callNum);
        dest.writeStringArray(menu);
        dest.writeString(url);
        dest.writeString(date);
        dest.writeInt(num);
        dest.writeInt(isCheck);
    }

    public void setIsCheck(int isCheck) { this.isCheck = isCheck; }

    public String getName() {
        return name;
    }

    public String getCallNum() {
        return callNum;
    }

    public String[] getMenu() {
        return menu;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public int getNum() {
        return num;
    }

    public int getIsCheck() { return isCheck; }
}