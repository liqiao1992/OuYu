package com.yizhan.ouyu.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.api.RetrofitRxjavaApi;
import com.yizhan.ouyu.entity.DribbbleFollowing;
import com.yizhan.ouyu.entity.DribbbleShot;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/25.
 */

public class DribbbleFollowingFragmentAdapter extends BaseRecyclerViewAdapter<DribbbleFollowing> {

    private RetrofitRxjavaApi retrofitRxjavaApi;

    public DribbbleFollowingFragmentAdapter(Context mContext) {
        super(mContext);
    }

    public DribbbleFollowingFragmentAdapter(Context mContext, RetrofitRxjavaApi retrofitRxjavaApi) {
        super(mContext);
        this.retrofitRxjavaApi = retrofitRxjavaApi;
    }

    @Override
    public RecyclerView.ViewHolder commonOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dribbble_following_fragment, parent, false);
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dribbble_following_fragment, null); 这样会导致CardView match_parent 无效
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

        loadShots(((DribbbleFollowingHolder) holder), position);
    }

    private void loadShots(final DribbbleFollowingHolder holder, int position) {
        Log.i("fuck", "加载shots++++++++++++++++++++++++++++++++" + position + "userId:" + data.get(position).getFollowee().getId());
        if(holder.recyclerView.getAdapter()!=null && ((DribbbleFollowingItemAdapter)holder.recyclerView.getAdapter()).data.isEmpty()) {
            Log.e("fuck","加载数据"+position);
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
                            ((DribbbleFollowingItemAdapter)holder.recyclerView.getAdapter()).clear();
                            ((DribbbleFollowingItemAdapter)holder.recyclerView.getAdapter()).addDataList(dribbbleShots);
                        }
                    });
        }
    }

    private class DribbbleFollowingHolder extends RecyclerView.ViewHolder {

        private CircleImageView userImageView;
        private TextView userNameTextView, locationTextView, bioTextView;
        private RecyclerView recyclerView;

        public DribbbleFollowingHolder(View itemView) {
            super(itemView);
            userImageView = (CircleImageView) itemView.findViewById(R.id.item_dribbble_following_user_imageView);
            userNameTextView = (TextView) itemView.findViewById(R.id.item_dribbble_following_user_textView);
            locationTextView = (TextView) itemView.findViewById(R.id.item_dribbble_following_locationg_textView);
            bioTextView = (TextView) itemView.findViewById(R.id.item_dribbble_following_bio_textView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_dribbble_following_recyclerView);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(itemView.getContext(), 3);
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            DribbbleFollowingItemAdapter madapter = new DribbbleFollowingItemAdapter(mContext);
            recyclerView.setAdapter(madapter);
        }
    }
}
