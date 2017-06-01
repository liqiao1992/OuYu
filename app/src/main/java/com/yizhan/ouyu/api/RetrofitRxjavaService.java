package com.yizhan.ouyu.api;


import android.util.Log;

import com.yizhan.ouyu.OuYuApplication;
import com.yizhan.ouyu.app.Constant;
import com.yizhan.ouyu.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2016/8/3.
 */
public class RetrofitRxjavaService {

    /**
     * baseurl
     */
    private static final String ZHIHU_HOST_URL = "http://news-at.zhihu.com/api/4/";

    private static final int CACHE_TIME_LONG = 60 * 60 * 24 * 7;

    private static OkHttpClient mOkHttpClient;

    public static RetrofitRxjavaService builder() {

        return new RetrofitRxjavaService();
    }

    public RetrofitRxjavaService() {
        provideOkHttpClient();
    }


    public RetrofitRxjavaApi ZhiHuLatestStoryApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZHIHU_HOST_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(RetrofitRxjavaApi.class);
    }

    public RetrofitRxjavaApi DribbbleApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.DRIBBBLE_API_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(RetrofitRxjavaApi.class);
    }

    public RetrofitRxjavaApi KaiYanApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.KAIYAN_API_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(RetrofitRxjavaApi.class);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };


        sslSocketFactory = new SSLSocketFactoryCompat(trustManager);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(sslSocketFactory, trustManager);
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    private void provideOkHttpClient() {
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };

        sslSocketFactory = new SSLSocketFactoryCompat(trustManager);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitRxjavaService.class) {
                if (mOkHttpClient == null) {
                    //设置Http缓存
                    Cache cache = new Cache(new File(OuYuApplication.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .sslSocketFactory(sslSocketFactory, trustManager)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }


    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (!NetWorkUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetworkConnected()) {
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_TIME_LONG)
                        .removeHeader("Pragma").build();
            }
        }
    };

}
