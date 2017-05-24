package com.yizhan.ouyu.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.base.BaseActivity;
import com.yizhan.ouyu.ui.fragment.MainFragment;
import com.yizhan.ouyu.ui.fragment.ZhiHuFragment;

/**
 * Created by lenovo on 2017/5/18.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null) {
            loadRootFragment(R.id.fragment_container, new MainFragment());
        }
    }


}
