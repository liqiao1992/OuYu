package com.yizhan.ouyu.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yizhan.ouyu.R;
import com.yizhan.ouyu.base.BaseFragment;

/**
 * Created by lenovo on 2017/5/26.
 */

public class AppWebViewFragment extends BaseFragment {

    private ProgressBar progressBar;
    private String url;
    private WebView webView;
    private Toolbar toolbar;

    public static AppWebViewFragment newInstance(String url){
        AppWebViewFragment appWebViewFragment=new AppWebViewFragment();
        Bundle bundle=new Bundle();
        bundle.putString("loadingUrl",url);
        appWebViewFragment.setArguments(bundle);
        return  appWebViewFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data=getArguments();
        if(data!=null){
            this.url=data.getString("loadingUrl");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_app_webview,null);
        initUi(view);
        initData();
        return view;
    }

    private void initUi(View view){
        toolbar= (Toolbar) view.findViewById(R.id.fragment_app_webview_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        progressBar= (ProgressBar) view.findViewById(R.id.fragment_app_webview_progressBar);
        webView= (WebView) view.findViewById(R.id.fragment_app_webview);
        progressBar.setMax(100);

    }

    private void  initData(){
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i("fuck",title);
                toolbar.setTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });
        webView.loadUrl(this.url);
    }

    @Override
    public boolean onBackPressedSupport() {
        if(webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onBackPressedSupport();
    }
}
