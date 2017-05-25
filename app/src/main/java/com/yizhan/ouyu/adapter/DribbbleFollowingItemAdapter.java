package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.entity.DribbbleShot;

/**
 * Created by lenovo on 2017/5/25.
 */

public class DribbbleFollowingItemAdapter extends BaseRecyclerViewAdapter<DribbbleShot> {

    public DribbbleFollowingItemAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dribbble_following_recyclerview, null);
        return new FollowingItemHolder(view);
    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getImages().getNormal())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(((FollowingItemHolder) holder).imageView);
    }

    private class FollowingItemHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public FollowingItemHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_dribbble_following_recyclerView_imageview);
        }
    }

}
