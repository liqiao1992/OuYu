package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.api.RetrofitRxjavaApi;
import com.yizhan.ouyu.entity.DribbbleFollowing;
import com.yizhan.ouyu.entity.DribbbleShot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/25.
 */

public class DribbbleFollowingFragmentAdapter extends BaseRecyclerViewAdapter<DribbbleFollowing> {

    private RetrofitRxjavaApi retrofitRxjavaApi;
    private Map<Integer, List<DribbbleShot>> shotDataMap = new HashMap<>();

    public DribbbleFollowingFragmentAdapter(Context mContext) {
        super(mContext);
    }

    public DribbbleFollowingFragmentAdapter(Context mContext, RetrofitRxjavaApi retrofitRxjavaApi) {
        super(mContext);
        this.retrofitRxjavaApi = retrofitRxjavaApi;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        Log.e("fuck", "recycle:" + holder.getAdapterPosition());
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dribbble_following_fragment, parent, false);
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dribbble_following_fragment, null); 这样会导致CardView match_parent 无效
        Log.i("fuck","commonOnCreateViewHolder......................");
        return new DribbbleFollowingHolder(view);
    }

    @Override
    public void commonOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getFollowee().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(((DribbbleFollowingHolder) holder).userImageView);
        ((DribbbleFollowingHolder) holder).userNameTextView.setText(data.get(position).getFollowee().getName());
        ((DribbbleFollowingHolder) holder).locationTextView.setText(data.get(position).getFollowee().getLocation());
        ((DribbbleFollowingHolder) holder).bioTextView.setText(Html.fromHtml(data.get(position).getFollowee().getBio()));

        Log.i("fuck", "commonOnBindViewHolder   position:" + position);
        if (shotDataMap.get(position) != null) {
            Log.i("fuck", "该位置开始已经有了数据" + position);
            List<DribbbleShot> dribbbleShots = shotDataMap.get(position);
            for (int i = 0; i < dribbbleShots.size(); i++) {
                Glide.with(mContext)
                        .load(dribbbleShots.get(i).getImages().getNormal())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(R.drawable.image_place_loader)
                        .centerCrop()
                        .into(((DribbbleFollowingHolder) holder).imageViewList.get(i));
            }
        } else {
            Log.i("fuck", "该位置还没有数据" + position);
            for (ImageView imageView : ((DribbbleFollowingHolder) holder).imageViewList) {
                imageView.setImageResource(R.drawable.image_place_loader);
            }
            loadShots(((DribbbleFollowingHolder) holder), position);
        }
    }


    private void loadShots(final DribbbleFollowingHolder holder, final int position) {
        holder.linearLayout.setTag(data.get(position).getFollowee().getId());
        this.retrofitRxjavaApi.getDribbbleShotByUserId(data.get(position).getFollowee().getId(), 1, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DribbbleShot>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<DribbbleShot> dribbbleShots) {
                        shotDataMap.put(position, dribbbleShots);
                        Log.i("fuck", "-----------将数据放入位置：" + position);
                        if ((int) holder.linearLayout.getTag() == data.get(position).getFollowee().getId()) {
                            Log.i("fuck","djjdjjjd");
                            for (int i = 0; i < dribbbleShots.size(); i++) {
                                Glide.with(mContext)
                                        .load(dribbbleShots.get(i).getImages().getNormal())
                                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                        .centerCrop()
                                        .into(holder.imageViewList.get(i));
                            }

                        }
                    }
                });
    }

    private class DribbbleFollowingHolder extends RecyclerView.ViewHolder {

        private CircleImageView userImageView;
        private TextView userNameTextView, locationTextView, bioTextView;
        private ImageView imageView1, imageView2, imageView3;
        private List<ImageView> imageViewList = new ArrayList<>();
        private LinearLayout linearLayout;

        public DribbbleFollowingHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_dribbble_following_fragment_linearLayout);
            imageView1 = (ImageView) itemView.findViewById(R.id.item_dribbble_following_shot_imageView1);
            imageView2 = (ImageView) itemView.findViewById(R.id.item_dribbble_following_shot_imageView2);
            imageView3 = (ImageView) itemView.findViewById(R.id.item_dribbble_following_shot_imageView3);
            imageViewList.add(imageView1);
            imageViewList.add(imageView2);
            imageViewList.add(imageView3);

            userImageView = (CircleImageView) itemView.findViewById(R.id.item_dribbble_following_user_imageView);
            userNameTextView = (TextView) itemView.findViewById(R.id.item_dribbble_following_user_textView);
            locationTextView = (TextView) itemView.findViewById(R.id.item_dribbble_following_locationg_textView);
            bioTextView = (TextView) itemView.findViewById(R.id.item_dribbble_following_bio_textView);
        }
    }
}
