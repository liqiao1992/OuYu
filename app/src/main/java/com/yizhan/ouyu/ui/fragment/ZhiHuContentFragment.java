package com.yizhan.ouyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.api.RetrofitRxjavaService;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.ZhiHuStoryContent;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/18.
 */

public class ZhiHuContentFragment extends BaseFragment {

    private WebView webView;
    private ImageView imageView;

    private int id;
    private String imageUrl;

    public static ZhiHuContentFragment newInstance(int id, String imageUrl) {
        ZhiHuContentFragment fragment = new ZhiHuContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("imageUrl", imageUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.id = bundle.getInt("id");
            this.imageUrl = bundle.getString("imageUrl");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_zhihu_content, null);
        initUi(rootView);
        initData();
        return rootView;
    }

    private void initUi(View rootView) {
        webView = (WebView) rootView.findViewById(R.id.fragment_zhihu_content_webview);
        imageView = (ImageView) rootView.findViewById(R.id.fragment_zhihu_content_imageview);
        Toolbar toolbar= (Toolbar) rootView.findViewById(R.id.fragment_zhihu_content_toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    private void initData() {
        RetrofitRxjavaService.builder().ZhiHuLatestStoryApi().getZhiHuStoryContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuStoryContent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ZhiHuStoryContent zhiHuStoryContent) {

                        Glide.with(getContext())
                                .load(zhiHuStoryContent.getImage())
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .centerCrop()
                                .into(imageView);

                        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
                        String html = "<html><head>" + css + "</head><body>" + zhiHuStoryContent.getBody() + "</body></html>";
                        html = html.replace("<div class=\"img-place-holder\">", "");
                        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
                    }
                });
    }
}
