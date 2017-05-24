package com.yizhan.ouyu.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lenovo on 2017/5/18.
 */

public class ZhiHuStory implements Parcelable{

    /**
     * "title": "Google I/O 完整盘点，这才是地球上最「性感」的发布会",
     "ga_prefix": "051809",
     "images": [
     "https://pic3.zhimg.com/v2-30fa81605900de562afaa26232366ae6.jpg"
     ],
     "multipic": true,
     "type": 0,
     "id": 9426650
     */
    private String title;
    private List<String> images;
    private String ga_prefix;
    private int type;
    private int id;
    private boolean multipic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeStringList(this.images);
        dest.writeString(this.ga_prefix);
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeByte(this.multipic ? (byte) 1 : (byte) 0);

    }

    protected ZhiHuStory(Parcel in) {
        this.title = in.readString();
        this.images = in.createStringArrayList();
        this.ga_prefix = in.readString();
        this.id = in.readInt();
        this.type=in.readInt();
        this.multipic = in.readByte() != 0;

    }

    public static final Parcelable.Creator<ZhiHuStory> CREATOR = new Parcelable.Creator<ZhiHuStory>() {
        @Override
        public ZhiHuStory createFromParcel(Parcel source) {
            return new ZhiHuStory(source);
        }

        @Override
        public ZhiHuStory[] newArray(int size) {
            return new ZhiHuStory[size];
        }
    };
}
