package com.yizhan.ouyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.base.BaseFragment;

/**
 * Created by lenovo on 2017/5/19.
 */

public class MainFragment extends BaseFragment {

    private BottomNavigationBar bottomNavigationBar;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD=2;
    private int preSelectedPos = 0;
    private BaseFragment[] mFragments = new BaseFragment[3];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null);
        if (savedInstanceState == null) {
//            loadRootFragment(R.id.fragment_main_container,new ZhiHuFragment());
            mFragments[FIRST] = new ZhiHuFragment();
            mFragments[SECOND] = new DribbbleFragment();
            mFragments[THIRD] = new DribbbleFragment();
            loadMultipleRootFragment(R.id.fragment_main_container, FIRST, mFragments[FIRST], mFragments[SECOND],mFragments[THIRD]);
        } else {
            mFragments[FIRST] = findChildFragment(ZhiHuFragment.class);
            mFragments[SECOND] = findChildFragment(DribbbleFragment.class);
        }
        initUi(rootView);
        return rootView;
    }

    private void initUi(View rootView) {
        bottomNavigationBar = (BottomNavigationBar) rootView.findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_message_white_24dp, "Nearby").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.ic_discover_white_24dp, "Find").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.ic_account_circle_white_24dp, "Categories").setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                showHideFragment(mFragments[position], mFragments[preSelectedPos]);
                preSelectedPos = position;
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
