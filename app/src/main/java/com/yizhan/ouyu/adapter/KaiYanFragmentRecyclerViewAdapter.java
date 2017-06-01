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
import com.yizhan.ouyu.entity.KaiYanVideo;

/**
 * Created by lenovo on 2017/6/1.
 */

public class KaiYanFragmentRecyclerViewAdapter extends BaseRecyclerViewAdapter<KaiYanVideo> {

    public KaiYanFragmentRecyclerViewAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_kaiyan_video_with_cover_recyclerview, parent, false);
        return new VideoListHolder(view);
    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getData().getCover().getFeed())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(((VideoListHolder) holder).imageView);
        ((VideoListHolder) holder).titleTv.setText(data.get(position).getData().getTitle());
        ((VideoListHolder) holder).categoryTv.setText("#"+data.get(position).getData().getCategory());
        StringBuilder stringBuilder = new StringBuilder();
        if (data.get(position).getData().getDuration() <= 3600) {
            int second = data.get(position).getData().getDuration() % 60;
            int minute = data.get(position).getData().getDuration() / 60;
            stringBuilder.append(minute + "'");
            stringBuilder.append(second + "''");
        }
        ((VideoListHolder) holder).durationTv.setText(stringBuilder);
    }


    private class VideoListHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTv, categoryTv, durationTv;

        public VideoListHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_recyclerView_imageView);
            titleTv = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_recyclerView_title_textView);
            categoryTv = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_recyclerView_category_textView);
            durationTv = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_recyclerView_duration_textView);
        }
    }
}
