package com.yizhan.ouyu.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2017/5/18.
 */

public class ZhiHuTopStory implements Parcelable {
    /**
     *"image": "https://pic4.zhimg.com/v2-f127090ca8a40972f9d8400b3bd5e6b7.jpg",
     "type": 0,
     "id": 9426650,
     "ga_prefix": "051809",
     "title": "Google I/O 完整盘点，这才是地球上最「性感」的发布会"
     */

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    protected ZhiHuTopStory(Parcel in) {
        this.id=in.readInt();
        this.type=in.readInt();
        this.ga_prefix=in.readString();
        this.title=in.readString();
        this.image=in.readString();
    }

    public static final Creator<ZhiHuTopStory> CREATOR = new Creator<ZhiHuTopStory>() {
        @Override
        public ZhiHuTopStory createFromParcel(Parcel in) {
            return new ZhiHuTopStory(in);
        }

        @Override
        public ZhiHuTopStory[] newArray(int size) {
            return new ZhiHuTopStory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.ga_prefix);
    }
}
