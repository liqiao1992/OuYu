package com.yizhan.ouyu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yizhan.ouyu.base.BaseFragment;

import java.util.List;

/**
 * Created by lenovo on 2017/5/23.
 */

public class DribbbleFragmentViewPagerAdapter extends FragmentStatePagerAdapter {


    private List<BaseFragment> fragmentList;
    private List<String> titleList;

    public DribbbleFragmentViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
