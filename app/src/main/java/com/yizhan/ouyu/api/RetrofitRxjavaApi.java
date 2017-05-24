package com.yizhan.ouyu.api;

import com.yizhan.ouyu.app.Constant;
import com.yizhan.ouyu.entity.DribbbleShot;
import com.yizhan.ouyu.entity.DribbbleShotComment;
import com.yizhan.ouyu.entity.ZhiHuLatestStory;
import com.yizhan.ouyu.entity.ZhiHuStoryContent;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lenovo on 2017/5/18.
 */

public interface RetrofitRxjavaApi {

    @GET("news/latest")
    Observable<ZhiHuLatestStory> getZhiHuLatestStory();

    @GET("news/before/{date}")
    Observable<ZhiHuLatestStory> getZhiHuBeforeStory(@Path("date") String date);

    @GET("news/{id}")
    Observable<ZhiHuStoryContent> getZhiHuStoryContent(@Path("id") int id);

    @Headers(Constant.DRIBBBLE_CLIENT_AUTH)
    @GET("shots")
    Observable<List<DribbbleShot>> getDribbbleShots(@Query("page") int page);

    @Headers(Constant.DRIBBBLE_CLIENT_AUTH)
    @GET("shots/{id}/comments")
    Observable<List<DribbbleShotComment>> getDribbbleShotComment(@Path("id") int id);
}
