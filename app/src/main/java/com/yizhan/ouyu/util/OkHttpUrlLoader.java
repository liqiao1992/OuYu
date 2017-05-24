package com.yizhan.ouyu.util;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.yizhan.ouyu.api.SSLSocketFactoryCompat;

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Created by lenovo on 2017/5/23.
 */

public class OkHttpUrlLoader implements ModelLoader<GlideUrl, InputStream> {

    /**
     * The default factory for {@link OkHttpUrlLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private static volatile OkHttpClient internalClient;
        private OkHttpClient client;

        private static OkHttpClient getInternalClient() {
            if (internalClient == null) {
                synchronized (Factory.class) {
                    if (internalClient == null) {
                        internalClient = getUnsafeOkHttpClient();
                    }
                }
            }
            return internalClient;
        }

        /**
         * Constructor for a new Factory that runs requests using a static singleton client.
         */
        public Factory() {
            this(getInternalClient());
        }

        /**
         * Constructor for a new Factory that runs requests using given client.
         */
        public Factory(OkHttpClient client) {
            this.client = client;
        }

        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new OkHttpUrlLoader(client);
        }

        @Override
        public void teardown() {
            // Do nothing, this instance doesn't own the client.
        }
    }

    private final OkHttpClient client;

    public OkHttpUrlLoader(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        return new OkHttpStreamFetcher(client, model);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            X509TrustManager trustManager;
            SSLSocketFactory sslSocketFactory ;

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

            try {
//                SSLContext sslContext;
//                sslContext = SSLContext.getInstance("TLSv1");
//                sslContext.init(null, new X509TrustManager[]{trustManager}, new SecureRandom());
                sslSocketFactory = new SSLSocketFactoryCompat(trustManager);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, trustManager);
//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });

            OkHttpClient okHttpClient =builder.build();

            Log.i("fuck", "返回client----------------------------------------------------------");
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}