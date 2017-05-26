package com.yizhan.ouyu.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2017/5/25.
 */

public class LoadingStatusViewHelper {
    private View loadingView;
    private View loadingErrorView;
    private View loadingEmptyView;

    public static class Builder {
        private View loadingView;
        private View loadingErrorView;
        private View loadingEmptyView;
        private ViewGroup containerView;
        private Context mContext;

        public Builder(ViewGroup containerView, Context context) {
            this.containerView = containerView;
            this.mContext = context;
        }

        public Builder loadingView(int resource) {
            loadingView = LayoutInflater.from(mContext).inflate(resource, containerView, false);
            containerView.addView(loadingView);
            loadingView.setVisibility(View.GONE);
            return this;
        }

        public Builder loadingErrorView(int resource) {
            loadingErrorView = LayoutInflater.from(mContext).inflate(resource, containerView, false);
            containerView.addView(loadingErrorView);
            loadingErrorView.setVisibility(View.GONE);
            return this;
        }

        public Builder loadingEmptyView(int resource) {
            loadingEmptyView = LayoutInflater.from(mContext).inflate(resource, containerView, false);
            containerView.addView(loadingEmptyView);
            loadingEmptyView.setVisibility(View.GONE);
            return this;
        }

        public LoadingStatusViewHelper build() {
            return new LoadingStatusViewHelper(this);
        }

    }

    private LoadingStatusViewHelper(Builder builder) {
        this.loadingView = builder.loadingView;
        this.loadingEmptyView = builder.loadingEmptyView;
        this.loadingErrorView = builder.loadingErrorView;
    }

    public void showLoadingView(){
        hideAllView();
        if(this.loadingView!=null){
            this.loadingView.setVisibility(View.VISIBLE);
        }
    }

    public void showLoadingErrorView(){
        hideAllView();
        if(this.loadingErrorView!=null){
            this.loadingErrorView.setVisibility(View.VISIBLE);
        }
    }

    public void showLoadingEmptyView(){
        hideAllView();
        if(this.loadingEmptyView!=null){
            this.loadingEmptyView.setVisibility(View.VISIBLE);
        }
    }

    private void hideAllView(){
        if(this.loadingView!=null){
            this.loadingView.setVisibility(View.GONE);
        }
        if(this.loadingEmptyView!=null){
            this.loadingEmptyView.setVisibility(View.GONE);
        }
        if(this.loadingErrorView!=null){
            this.loadingErrorView.setVisibility(View.GONE);
        }
    }
}
