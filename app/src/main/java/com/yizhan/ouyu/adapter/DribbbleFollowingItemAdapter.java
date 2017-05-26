package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private int userId;
    public DribbbleFollowingItemAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dribbble_following_recyclerview, null);

        Log.v("fuck","********************************************");
        Log.v("fuck","createViewHolder-------------------");
        Log.v("fuck","********************************************");

        return new FollowingItemHolder(view);
    }

    public void setUserId(int userId){
        this.userId=userId;
    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.v("fuck", "********************************************");
        Log.v("fuck","userId:"+this.userId);
        Log.v("fuck","position:"+position+",imageUrl:"+data.get(position).getImages().getNormal());
        Log.v("fuck", "********************************************");
        Log.v("fuck","                                         ");
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
