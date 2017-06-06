package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.KaiYanVideo;
import com.yizhan.ouyu.ui.fragment.KaiYanPlayFragment;
import com.yizhan.ouyu.util.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo on 2017/6/1.
 */

public class KaiYanFragmentAdapter extends BaseRecyclerViewAdapter<KaiYanVideo> {

    private final static int TYPE_VIDEO = 2;
    private final static int TYPE_TEXT_HEADER = 3;
    private final static int TYPE_TEXT_FOOTER = 4;
    private final static int TYPE_VIDEO_COLLECTION_WITH_COVER = 5;
    private final static int TYPE_VIDEO_COLLECTION_Of_FOLLOW = 6;
    private final static int TYPE_ERROR = -1;
    private final static int TYPE_BANNER = 7;
    private BaseFragment mFragment;
    private Typeface textFont;

    public KaiYanFragmentAdapter(Context mContext, BaseFragment mFragment) {
        super(mContext);
        this.mFragment = mFragment;
        AssetManager assets = mContext.getAssets();
        textFont = Typeface.createFromAsset(assets, "fonts/Lobster-1.4.otf");
    }

    public KaiYanFragmentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_VIDEO) {
            View videoView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_kaiyan_video, parent, false);
            return new VideoHolder(videoView);
        } else if (viewType == TYPE_TEXT_FOOTER) {
            View textView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_kaiyan_text, parent, false);
            return new TextHolder(textView);
        } else if (viewType == TYPE_VIDEO_COLLECTION_WITH_COVER) {
            View videoWithCoverView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_kaiyan_video_with_cover, parent, false);
            return new VideoWithCoverHolder(videoWithCoverView);
        } else if (viewType == TYPE_TEXT_HEADER) {
            View textView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_kaiyan_text, parent, false);
            return new TextHolder(textView);
        } else if (viewType == TYPE_VIDEO_COLLECTION_Of_FOLLOW) {
            View videoWithCoverView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_kaiyan_video_with_cover, parent, false);
            return new VideoWithCoverHolder(videoWithCoverView);
        } else {
            View videoView = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_kaiyan_video, parent, false);
            return new VideoHolder(videoView);
        }

    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_VIDEO) {
            Glide.with(mContext)
                    .load(data.get(position).getData().getCover().getFeed())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(((VideoHolder) holder).imageView);
            ((VideoHolder) holder).titleTv.setText(data.get(position).getData().getTitle());
            ((VideoHolder) holder).categoryTv.setText("#" + data.get(position).getData().getCategory());
            StringBuilder stringBuilder = new StringBuilder();
            if (data.get(position).getData().getDuration() <= 3600) {
                int second = data.get(position).getData().getDuration() % 60;
                int minute = data.get(position).getData().getDuration() / 60;
                stringBuilder.append(minute + "'");
                stringBuilder.append(second + "''");
            }
            ((VideoHolder) holder).durationTv.setText(stringBuilder);
        } else if (type == TYPE_TEXT_FOOTER) {
            ((TextHolder) holder).textView.setText(data.get(position).getData().getText());
        } else if (type == TYPE_VIDEO_COLLECTION_WITH_COVER) {
            Glide.with(mContext)
                    .load(data.get(position).getData().getHeader().getCover())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(((VideoWithCoverHolder) holder).coverImageView);
            ((VideoWithCoverHolder) holder).linearLayout.setVisibility(View.GONE);
            ((KaiYanFragmentRecyclerViewAdapter) ((VideoWithCoverHolder) holder).recyclerView.getAdapter()).clear();
            ((KaiYanFragmentRecyclerViewAdapter) ((VideoWithCoverHolder) holder).recyclerView.getAdapter()).addDataList(data.get(position).getData().getItemList());
        } else if (type == TYPE_TEXT_HEADER) {
            if (data.get(position).getData().getFont().equals("lobster")) {
                ((TextHolder) holder).textView.setTypeface(textFont);
                ((TextHolder) holder).textView.setTextColor(mContext.getResources().getColor(android.R.color.black));
            }
            ((TextHolder) holder).textView.setText(data.get(position).getData().getText());
        } else if (type == TYPE_VIDEO_COLLECTION_Of_FOLLOW) {
            Glide.with(mContext)
                    .load(data.get(position).getData().getHeader().getCover())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(((VideoWithCoverHolder) holder).coverImageView);
            ((VideoWithCoverHolder) holder).linearLayout.setVisibility(View.VISIBLE);
            ((VideoWithCoverHolder) holder).titleTv.setText(data.get(position).getData().getHeader().getTitle());
            ((VideoWithCoverHolder) holder).descTv.setText(data.get(position).getData().getHeader().getDescription());

            List<String> iconUrls = data.get(position).getData().getHeader().getIconList();
            for (int i = 0; i < iconUrls.size(); i++) {
                Glide.with(mContext)
                        .load(iconUrls.get(i))
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .centerCrop()
                        .into(((VideoWithCoverHolder) holder).iconList.get(i));
            }


            ((KaiYanFragmentRecyclerViewAdapter) ((VideoWithCoverHolder) holder).recyclerView.getAdapter()).clear();
            ((KaiYanFragmentRecyclerViewAdapter) ((VideoWithCoverHolder) holder).recyclerView.getAdapter()).addDataList(data.get(position).getData().getItemList());
        }else if(type==TYPE_BANNER){
            Glide.with(mContext)
                    .load(data.get(position).getData().getImage())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(((VideoHolder) holder).imageView);
            ((VideoHolder) holder).titleTv.setVisibility(View.GONE);
            ((VideoHolder) holder).categoryTv.setVisibility(View.GONE);
            ((VideoHolder) holder).durationTv.setVisibility(View.GONE);
            ((VideoHolder) holder).dividerTv.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getType().equals("video")) {
            return TYPE_VIDEO;
        } else if (data.get(position).getType().equals("videoCollectionOfFollow")) {
            return TYPE_VIDEO_COLLECTION_Of_FOLLOW;
        } else if (data.get(position).getType().equals("textHeader")) {
            return TYPE_TEXT_HEADER;
        } else if (data.get(position).getType().equals("textFooter")) {
            return TYPE_TEXT_FOOTER;
        } else if (data.get(position).getType().equals("videoCollectionWithCover")) {
            return TYPE_VIDEO_COLLECTION_WITH_COVER;
        } else if (data.get(position).getType().equals("banner2")) {
            return TYPE_BANNER;
        } else {
            return TYPE_ERROR;
        }
    }

    private class VideoHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTv, categoryTv, durationTv,dividerTv;

        public VideoHolder(View itemView) {
            super(itemView);
            dividerTv= (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_divider_textView);
            imageView = (ImageView) itemView.findViewById(R.id.item_fragment_kaiyan_video_imageView);
            titleTv = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_title_textView);
            categoryTv = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_category_textView);
            durationTv = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_duration_textView);
        }
    }

    private class TextHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public TextHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_text_tv);
        }
    }

    private class VideoWithCoverHolder extends RecyclerView.ViewHolder {

        private ImageView coverImageView;
        private RecyclerView recyclerView;
        private LinearLayout linearLayout;
        private CircleImageView icon1, icon2, icon3;
        private TextView titleTv, descTv;
        private List<CircleImageView> iconList = new ArrayList<>();

        public VideoWithCoverHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_linearLayout);

            icon1 = (CircleImageView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_icon1);
            icon2 = (CircleImageView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_icon2);
            icon3 = (CircleImageView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_icon3);
            titleTv = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_title_textView);
            descTv = (TextView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_desc_textView);

            iconList.add(icon1);
            iconList.add(icon2);
            iconList.add(icon3);
            coverImageView = (ImageView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_imageView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_fragment_kaiyan_video_with_cover_recyclerView);
            LinearLayoutManager llm = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(llm);
            final KaiYanFragmentRecyclerViewAdapter adapter = new KaiYanFragmentRecyclerViewAdapter(mContext);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, adapter.getItem(position).getData().getTitle(), Toast.LENGTH_LONG).show();
                    mFragment.start(KaiYanPlayFragment.newInstance(adapter.getItem(position)));
                }
            });
        }
    }
}
