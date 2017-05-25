package com.yizhan.ouyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.adapter.DribbbleShotCommentAdapter;
import com.yizhan.ouyu.api.RetrofitRxjavaService;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.DribbbleShot;
import com.yizhan.ouyu.entity.DribbbleShotComment;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/24.
 */

public class DribbbleShotDetailFragment extends BaseFragment {

    private Toolbar toolbar;
    private DribbbleShot dribbbleShot;
    private ImageView imageView,userImageView;
    private TextView titleTextView,userNameTextView,descTextView,commentNumTextView;
    private RecyclerView recyclerView;
    private DribbbleShotCommentAdapter adapter;


    public static  DribbbleShotDetailFragment newInstance(DribbbleShot data){
        DribbbleShotDetailFragment fragment=new DribbbleShotDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("data",data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            dribbbleShot=bundle.getParcelable("data");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dribbble_shot_detail,null);
        initUi(view);
        initData();
        return view;
    }

    private void initUi(View view){
        commentNumTextView= (TextView) view.findViewById(R.id.fragment_dribbble_shot_detail_response_num_textView);
        recyclerView= (RecyclerView) view.findViewById(R.id.fragment_dribbble_shot_detail_recyclerView);
        descTextView= (TextView) view.findViewById(R.id.fragment_dribbble_shot_detail_desc_textView);
        imageView= (ImageView) view.findViewById(R.id.fragment_dribbble_shot_detail_imageView);
        userImageView= (ImageView) view.findViewById(R.id.fragment_dribbble_shot_detail_user_imageView);
        titleTextView= (TextView) view.findViewById(R.id.fragment_dribbble_shot_detail_title_textView);
        userNameTextView= (TextView) view.findViewById(R.id.fragment_dribbble_shot_detail_user_textView);
        toolbar= (Toolbar) view.findViewById(R.id.fragment_dribbble_shot_detail_toolBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

    }

    private void initData(){

        Glide.with(getContext())
                .load(dribbbleShot.getImages().getHidpi()!=null?dribbbleShot.getImages().getHidpi():dribbbleShot.getImages().getNormal())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(imageView);

        Glide.with(getContext())
                .load(dribbbleShot.getUser().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(userImageView);
        userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"sdsfsd",Toast.LENGTH_LONG).show();
                //start(new DribbbleFollowingFragment());
            }
        });
        titleTextView.setText(dribbbleShot.getTitle());
        userNameTextView.setText(dribbbleShot.getUser().getName());
        if(dribbbleShot.getDescription()!=null ){
            descTextView.setVisibility(View.VISIBLE);
            descTextView.setText(Html.fromHtml(dribbbleShot.getDescription()));
        }
        adapter=new DribbbleShotCommentAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        loadComments();
    }

    private void loadComments(){
        RetrofitRxjavaService.builder().DribbbleApi().getDribbbleShotComment(dribbbleShot.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DribbbleShotComment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<DribbbleShotComment> dribbbleShotComments) {
                        commentNumTextView.setText(dribbbleShotComments.size()+" Responses");
                          adapter.addDataList(dribbbleShotComments);
                    }
                });
    }
}
