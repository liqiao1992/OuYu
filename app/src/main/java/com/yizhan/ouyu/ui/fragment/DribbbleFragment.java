package com.yizhan.ouyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.yizhan.ouyu.R;
import com.yizhan.ouyu.adapter.DribbbleFragmentViewPagerAdapter;
import com.yizhan.ouyu.api.RetrofitRxjavaService;
import com.yizhan.ouyu.app.Constant;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.DribbbleShot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/22.
 */

public class DribbbleFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_dribbble,null);
        initUi(rootView);
        initData();
        return rootView;
    }

    private void initUi(View view){
        tabLayout= (TabLayout) view.findViewById(R.id.fragment_dribbble_tabLayout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        viewPager= (ViewPager) view.findViewById(R.id.fragment_dribbble_viewPager );
    }

    private void initData(){
        List<BaseFragment> fragmentList=new ArrayList<>();
        fragmentList.add(new DribbbleShotFragment());
        fragmentList.add(new DribbbleFollowingFragment());
        List<String> titleList=new ArrayList<>();
        titleList.add("SHOT");
        titleList.add("FOLLOWING");
        viewPager.setAdapter(new DribbbleFragmentViewPagerAdapter(getChildFragmentManager(),fragmentList,titleList));
        tabLayout.setupWithViewPager(viewPager);

    }


}
