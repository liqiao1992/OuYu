package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.entity.ZhiHuStory;


/**
 * Created by lenovo on 2017/5/19.
 */

public class ZhiHuFragmentAdapter extends BaseRecyclerViewAdapter<ZhiHuStory> {


    public ZhiHuFragmentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_zhihu_fragment, null);
        return new MyHolder(view);
    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getImages().get(0))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(((MyHolder) holder).imageView);
        ((MyHolder) holder).textView.setText(data.get(position).getTitle());
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_zhihu_fragment_imageview);
            textView = (TextView) itemView.findViewById(R.id.item_zhihu_framgent_textview);
        }
    }
}
