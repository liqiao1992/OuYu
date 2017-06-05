package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by lenovo on 2017/6/5.
 */

public class KaiYanPlayFragmentAdapter extends BaseRecyclerViewAdapter<KaiYanVideo> {

    private final static int TYPE_TEXT_CARD = 2;
    private final static int TYPE_VIDEO_SMALL_CARD = 4;
    private final static int TYPE_ERROR = -1;

    public KaiYanPlayFragmentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TEXT_CARD) {
            View textView = LayoutInflater.from(mContext).inflate(R.layout.item_play_fragment_textcard,parent,false);
            return new TextCardHolder(textView);
        } else {
            View videoView = LayoutInflater.from(mContext).inflate(R.layout.item_play_fragment_videocard, parent,false);
            return new VideoCardHolder(videoView);
        }
    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int type=getItemViewType(position+headViewNum);
        if(type==TYPE_TEXT_CARD){
            ((TextCardHolder)holder).textView.setText(data.get(position).getData().getText()+"  >");
        }else if(type==TYPE_VIDEO_SMALL_CARD){
            Glide.with(mContext)
                    .load(data.get(position).getData().getCover().getFeed())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(((VideoCardHolder)holder).imageView);
            ((VideoCardHolder)holder).titleTv.setText(data.get(position).getData().getTitle());
            ((VideoCardHolder)holder).categoryTv.setText(data.get(position).getData().getCategory());
            StringBuilder stringBuilder = new StringBuilder();
            if (data.get(position).getData().getDuration() <= 3600) {
                int second = data.get(position).getData().getDuration() % 60;
                int minute = data.get(position).getData().getDuration() / 60;
                stringBuilder.append(minute + "'");
                stringBuilder.append(second + "''");
            }
            ((VideoCardHolder)holder).durationTv.setText(stringBuilder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getHeaderView() != null && position == 0) {
            return TYPE_HEADER;
        } else if (data.get(position-headViewNum).getType().equals("videoSmallCard")) {
            return TYPE_VIDEO_SMALL_CARD;
        } else if (data.get(position-headViewNum).getType().equals("textCard")) {
            return TYPE_TEXT_CARD;
        } else {
            return TYPE_ERROR;
        }

    }

    private class TextCardHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public TextCardHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_play_fragment_textcard_tv);
        }
    }

    private class VideoCardHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTv, categoryTv, durationTv;

        public VideoCardHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_play_fragment_videoCard_imageView);
            titleTv = (TextView) itemView.findViewById(R.id.item_play_fragment_videoCard_title_textView);
            categoryTv = (TextView) itemView.findViewById(R.id.item_play_fragment_videoCard_category_textView);
            durationTv = (TextView) itemView.findViewById(R.id.item_play_fragment_videoCard_duration_textView);
        }
    }
}
