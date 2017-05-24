package com.yizhan.ouyu.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lenovo on 2017/5/18.
 */

public class ZhiHuLatestStory {

    /**
     * "date": "20170518",
     * "stories": [],
     * "top_stories": []
     */
    private String date;
    private List<ZhiHuStory> stories;
    private List<ZhiHuTopStory> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZhiHuStory> getStories() {
        return stories;
    }

    public void setStories(List<ZhiHuStory> stories) {
        this.stories = stories;
    }

    public List<ZhiHuTopStory> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<ZhiHuTopStory> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public String toString() {
        return "ZhiHuLatestStory{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
