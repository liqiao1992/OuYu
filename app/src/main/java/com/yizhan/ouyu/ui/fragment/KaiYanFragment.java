package com.yizhan.ouyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yizhan.ouyu.R;
import com.yizhan.ouyu.adapter.BaseRecyclerViewAdapter;
import com.yizhan.ouyu.adapter.KaiYanFragmentAdapter;
import com.yizhan.ouyu.api.RetrofitRxjavaApi;
import com.yizhan.ouyu.api.RetrofitRxjavaService;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.KaiYanVideoList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/6/1.
 */

public class KaiYanFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private KaiYanFragmentAdapter adapter;
    private RetrofitRxjavaApi retrofitRxjavaApi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kaiyan, null);
        initUi(rootView);
        initData();
        return rootView;
    }

    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_kaiyan_recyclerView);

    }

    private void initData() {
        retrofitRxjavaApi = RetrofitRxjavaService.builder().KaiYanApi();
        adapter = new KaiYanFragmentAdapter(getContext(),((BaseFragment)getParentFragment()));
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(adapter.getItem(position).getType().equals("video")) {
                    ((BaseFragment) getParentFragment()).start(KaiYanPlayFragment.newInstance(adapter.getItem(position)));
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        retrofitRxjavaApi.getKaiYanVideo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<KaiYanVideoList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(KaiYanVideoList kaiYanVideoList) {
                         adapter.addDataList(kaiYanVideoList.getItemList());
                    }
                });
    }
}
