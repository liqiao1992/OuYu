package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.entity.DribbbleShot;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo on 2017/5/23.
 */

public class DribbbleShotFragmentAdapter extends BaseRecyclerViewAdapter<DribbbleShot> {

    public DribbbleShotFragmentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_dribbble_shot_fragment,null);
        return new DribbbleShotViewHolder(view);
    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getImages().getNormal())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(((DribbbleShotViewHolder)holder).imageView);
        ((DribbbleShotViewHolder)holder).views_count.setText(data.get(position).getViews_count()+"");
        ((DribbbleShotViewHolder)holder).like_count.setText(data.get(position).getLikes_count()+"");
        ((DribbbleShotViewHolder)holder).comment_count.setText(data.get(position).getComments_count()+"");
        ((DribbbleShotViewHolder)holder).titleTextView.setText(data.get(position).getTitle());
        Glide.with(mContext)
                .load(data.get(position).getUser().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(((DribbbleShotViewHolder)holder).userImageView);
        ((DribbbleShotViewHolder)holder).userTextView.setText(data.get(position).getUser().getName());
    }

    private class DribbbleShotViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView,viewImageView,likeImageView,commentImageView;
        private CircleImageView userImageView;
        private TextView views_count,like_count,comment_count,userTextView,titleTextView;
        public DribbbleShotViewHolder(View itemView) {
            super(itemView);
            viewImageView= (ImageView) itemView.findViewById(R.id.item_dribbble_shot_fragment_view_imageView);
            likeImageView=(ImageView) itemView.findViewById(R.id.item_dribbble_shot_fragment_like_imageView);
            commentImageView=(ImageView) itemView.findViewById(R.id.item_dribbble_shot_fragment_comment_imageView);
            viewImageView.setColorFilter(Color.LTGRAY);
            likeImageView.setColorFilter(Color.LTGRAY);
            commentImageView.setColorFilter(Color.LTGRAY);
            titleTextView= (TextView) itemView.findViewById(R.id.item_dribbble_shot_fragment_title_textView);
            imageView= (ImageView) itemView.findViewById(R.id.item_dribbble_shot_fragment_imageView);
            views_count= (TextView) itemView.findViewById(R.id.item_dribbble_shot_fragment_view_textView);
            like_count= (TextView) itemView.findViewById(R.id.item_dribbble_shot_fragment_like_textView);
            comment_count= (TextView) itemView.findViewById(R.id.item_dribbble_shot_fragment_comment_textView);
            userImageView=(CircleImageView) itemView.findViewById(R.id.item_dribbble_shot_fragment_user_imageView);
            userTextView= (TextView) itemView.findViewById(R.id.item_dribbble_shot_fragment_user_textView);
        }
    }
}
