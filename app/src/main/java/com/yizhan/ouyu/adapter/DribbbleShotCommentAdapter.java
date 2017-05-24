package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.entity.DribbbleShotComment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo on 2017/5/24.
 */

public class DribbbleShotCommentAdapter extends BaseRecyclerViewAdapter<DribbbleShotComment> {
    public DribbbleShotCommentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_dribbble_shot_comment,null);
        return new CommentViewHolder(view);
    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getUser().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(((CommentViewHolder)holder).userImageView);
        ((CommentViewHolder)holder).userNameTextView.setText(data.get(position).getUser().getName());
        ((CommentViewHolder)holder).commentTextView.setText(Html.fromHtml(data.get(position).getBody()));
        ((CommentViewHolder)holder).dateTimeTextView.setText(data.get(position).getUpdated_at());
        ((CommentViewHolder)holder).likeNumTextView.setText(data.get(position).getLikes_count()+"");
    }

    private class CommentViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView userImageView;
        private TextView userNameTextView,commentTextView,dateTimeTextView,likeNumTextView;
        private ImageView likeImageView;
        public CommentViewHolder(View itemView) {
            super(itemView);
            userImageView= (CircleImageView) itemView.findViewById(R.id.item_dribbble_shot_comment_user_imageView);
            likeImageView= (ImageView) itemView.findViewById(R.id.item_dribbble_shot_comment_like_imageView);
            likeImageView.setColorFilter(Color.LTGRAY);
            userNameTextView= (TextView) itemView.findViewById(R.id.item_dribbble_shot_comment_user_textView);
            commentTextView= (TextView) itemView.findViewById(R.id.item_dribbble_shot_comment_body_textView);
            dateTimeTextView= (TextView) itemView.findViewById(R.id.item_dribbble_shot_comment_date_textView);
            likeNumTextView= (TextView) itemView.findViewById(R.id.item_dribbble_shot_comment_like_textView);
        }
    }
}
