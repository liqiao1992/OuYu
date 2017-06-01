package com.yizhan.ouyu.entity;

import java.util.List;

/**
 * Created by lenovo on 2017/6/1.
 */

public class KaiYanVideoList {

    private List<KaiYanVideo> itemList;
    public List<KaiYanVideo> getItemList() {
        return itemList;
    }

    public void setItemList(List<KaiYanVideo> itemList) {
        this.itemList = itemList;
    }

    /**
     * count : 14
     * total : 0
     * nextPageUrl : http://baobab.kaiyanapp.com/api/v4/tabs/selected?date=1496192400000&num=2&page=2
     * date : 1496278800000
     * nextPublishTime : 1496322000000
     * dialog : null
     * topIssue : null
     */

    private int count;
    private int total;
    private String nextPageUrl;
    private long date;
    private long nextPublishTime;
    private Object dialog;
    private Object topIssue;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public Object getDialog() {
        return dialog;
    }

    public void setDialog(Object dialog) {
        this.dialog = dialog;
    }

    public Object getTopIssue() {
        return topIssue;
    }

    public void setTopIssue(Object topIssue) {
        this.topIssue = topIssue;
    }
}
