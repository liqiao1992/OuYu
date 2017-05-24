package com.yizhan.ouyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yizhan.ouyu.R;
import com.yizhan.ouyu.base.BaseFragment;

/**
 * Created by lenovo on 2017/5/23.
 */

public class DribbbleFollowingFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView=inflater.inflate(R.layout.fragment_dribbble_following,null);
        return rootView;
    }
}
