package com.yizhan.ouyu.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yizhan.ouyu.R;
import com.yizhan.ouyu.adapter.DribbbleFollowingFragmentAdapter;
import com.yizhan.ouyu.api.RetrofitRxjavaApi;
import com.yizhan.ouyu.api.RetrofitRxjavaService;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.DribbbleFollowing;
import com.yizhan.ouyu.entity.DribbbleShot;
import com.yizhan.ouyu.util.LoadingStatusViewHelper;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/23.
 */

public class DribbbleFollowingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DribbbleFollowingFragmentAdapter adapter;
    private int per_page = 6;
    private RetrofitRxjavaApi retrofitRxjavaApi;
    private int page = 1;
    private FrameLayout loadingLl;
    private LoadingStatusViewHelper loadingStatusViewHelper;
    private boolean isLoading = false;
    private int VISIBLE_LEFT_COUNT = 2;//此值表示当RecyclerView滑动到距离底部还有几个View的时候准备加载更多

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dribbble_following, null);
        initUi(rootView);
//        initData();
        return rootView;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        loadingStatusViewHelper.showLoadingView();
        initData();
    }

    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_dribbble_following_recyclerView);
        loadingLl = (FrameLayout) view.findViewById(R.id.fragment_dribbble_following_container_frameLayout);
        loadingStatusViewHelper = new LoadingStatusViewHelper.Builder(loadingLl, getContext())
                .loadingView(R.layout.loading_layout)
                .loadingErrorView(R.layout.loading_error_layout)
                .build();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleChildCount = recyclerView.getChildCount();
                int totalCount = recyclerView.getLayoutManager().getItemCount();
                int firstVisiblePos = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (!isLoading && totalCount - visibleChildCount - firstVisiblePos < VISIBLE_LEFT_COUNT) {
                    isLoading = true;
                    page++;
                    loadingFollowing(page);
                }
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_dribbble_following_swipRefresh_layout);
    }

    private void initData() {
        retrofitRxjavaApi = RetrofitRxjavaService.builder().DribbbleApi();
        adapter = new DribbbleFollowingFragmentAdapter(getContext(), retrofitRxjavaApi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setEnabled(false);
        recyclerView.setAdapter(adapter);

        loadingFollowing(page);
    }

    private void loadingFollowing(final int page) {
        retrofitRxjavaApi.getDribbbleFollowings(page, per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DribbbleFollowing>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                        isLoading = false;
                    }

                    @Override
                    public void onNext(List<DribbbleFollowing> dribbbleFollowings) {

                        if (page == 1) {
                            if (loadingLl.getVisibility() == View.VISIBLE) {
                                loadingLl.setVisibility(View.GONE);
                            }
                            if (swipeRefreshLayout.getVisibility() == View.GONE) {
                                swipeRefreshLayout.setVisibility(View.VISIBLE);
                            }
                        }
                        if (page == 1 && swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        adapter.addDataList(dribbbleFollowings);
                        isLoading = false;
                    }
                });

    }


    @Override
    public void onRefresh() {
        page = 1;
        adapter.clear();
        loadingFollowing(page);
    }
}
