package com.yizhan.ouyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yizhan.ouyu.R;
import com.yizhan.ouyu.adapter.BaseRecyclerViewAdapter;
import com.yizhan.ouyu.adapter.DribbbleShotFragmentAdapter;
import com.yizhan.ouyu.api.RetrofitRxjavaApi;
import com.yizhan.ouyu.api.RetrofitRxjavaService;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.DribbbleShot;
import com.yizhan.ouyu.util.LoadingStatusViewHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/22.
 */

public class DribbbleShotFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private DribbbleShotFragmentAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int page = 1;
    private RetrofitRxjavaApi retrofitRxjavaApi;
    private FrameLayout loadingLl;
//    private View loadingErrorView;

    private LoadingStatusViewHelper loadingStatusViewHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dribbble_shot, null);
        initUi(rootView);
        initData();
        return rootView;
    }

    private void initUi(View view) {
        loadingLl = (FrameLayout) view.findViewById(R.id.fragment_dribbble_shot_container_frameLayout);
        loadingStatusViewHelper = new LoadingStatusViewHelper.Builder(loadingLl, getContext())
                .loadingView(R.layout.loading_layout)
                .loadingErrorView(R.layout.loading_error_layout)
                .build();
        loadingStatusViewHelper.showLoadingView();
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_dribbble_shot_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_dribbble_shot_swipRefresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && position + 1 == recyclerView.getAdapter().getItemCount()) {
                    page++;
                    loadDribbbleShots(page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

    }

    private void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new DribbbleShotFragmentAdapter(getContext());
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_LONG).show();
                ((BaseFragment) (getParentFragment().getParentFragment())).start(DribbbleShotDetailFragment.newInstance(adapter.getItem(position)));
//                ( getParentFragment()).start(new DribbbleShotDetailFragment());
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        retrofitRxjavaApi = RetrofitRxjavaService.builder().DribbbleApi();
        loadDribbbleShots(page);
    }

    private void loadDribbbleShots(final int page) {
        retrofitRxjavaApi.getDribbbleShots(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DribbbleShot>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        loadingStatusViewHelper.showLoadingErrorView();
                        if (swipeRefreshLayout.getVisibility() == View.GONE) {
                            swipeRefreshLayout.setVisibility(View.VISIBLE);
                        }
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onNext(List<DribbbleShot> dribbbleShots) {

                        if (page == 1) {
                            if (loadingLl.getVisibility() == View.VISIBLE) {
                                loadingLl.setVisibility(View.GONE);
                            }
                            if (swipeRefreshLayout.getVisibility() == View.GONE) {
                                swipeRefreshLayout.setVisibility(View.VISIBLE);
                            }
                        }

                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        adapter.addDataList(dribbbleShots);
                    }
                });
    }

    @Override
    public void onRefresh() {
        page = 1;
        adapter.clear();
        loadDribbbleShots(page);
    }
}
