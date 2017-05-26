package com.yizhan.ouyu.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.adapter.BaseRecyclerViewAdapter;
import com.yizhan.ouyu.adapter.ZhiHuFragmentAdapter;
import com.yizhan.ouyu.api.RetrofitRxjavaApi;
import com.yizhan.ouyu.api.RetrofitRxjavaService;
import com.yizhan.ouyu.base.BaseActivity;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.ZhiHuLatestStory;
import com.yizhan.ouyu.entity.ZhiHuStory;
import com.yizhan.ouyu.entity.ZhiHuTopStory;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/5/18.
 */

public class ZhiHuFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String date;
    private ZhiHuFragmentAdapter adapter;
    private Banner banner;
    private List<ZhiHuTopStory> images = new ArrayList<>();
    private RetrofitRxjavaApi retrofitRxjavaApi;
    private boolean isLoading=false;
    private int VISIBLE_LEFT_COUNT=4;//此值表示当RecyclerView滑动到距离底部还有几个View的时候准备加载更多
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_zhihu, null);
        initUi(rootView);
        initData();
        return rootView;
    }

    private void initUi(View rootView) {
        toolbar = (Toolbar) rootView.findViewById(R.id.fragment_zhihu_toolbar);
        toolbar.setTitle("今日新闻");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_zhihu_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.fragment_zhihu_swipRefresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleChildCount=recyclerView.getChildCount();//这个是屏幕中所见的View的个数
                int totalCount= recyclerView.getLayoutManager().getItemCount();//内部就是adapter.getItemCount
                int firstVisiblePos=((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                Log.e("fuck","visibleChildCount:"+visibleChildCount+" totalCount:"+totalCount+" firstVisiblePos:"+firstVisiblePos);
                if(!isLoading&&totalCount-firstVisiblePos-visibleChildCount<VISIBLE_LEFT_COUNT){
                    Log.e("fuck","准备加载更多");
                    isLoading = true;
                    loadMoreStory();
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastvisiblePos = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastvisiblePos + 1 == recyclerView.getAdapter().getItemCount()) {
//                    loadMoreStory();
//                }
            }
        });

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(((ZhiHuTopStory) path).getImage())
                    .centerCrop()
                    .into(imageView);
        }
    }


    private void initData() {
        retrofitRxjavaApi= RetrofitRxjavaService.builder().ZhiHuLatestStoryApi();
        adapter = new ZhiHuFragmentAdapter(getContext());
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_header_view, null);
        banner = (Banner) headView.findViewById(R.id.item_header_view_banner);
        banner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
//                        Toast.makeText(getContext(),images.get(position).getId()+"",Toast.LENGTH_LONG).show();
                        ((BaseFragment) getParentFragment()).start(ZhiHuContentFragment.newInstance(images.get(position).getId(), images.get(position).getImage()));
                    }
                })
                .start();
        adapter.setHeaderView(headView);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Toast.makeText(getContext(), "" + position, Toast.LENGTH_LONG).show();
                ((BaseFragment) getParentFragment()).start(ZhiHuContentFragment.newInstance(adapter.getItem(position).getId(), adapter.getItem(position).getImages().get(0)));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        loadingLatestStory();
    }

    private void loadMoreStory(){
        Log.i("fuck","进入加载更多页面");
        retrofitRxjavaApi.getZhiHuBeforeStory(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhiHuLatestStory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        isLoading=false;
                    }

                    @Override
                    public void onNext(ZhiHuLatestStory zhiHuLatestStory) {
                        System.out.print(zhiHuLatestStory.toString());
                        isLoading=false;
                        date = zhiHuLatestStory.getDate();
                        adapter.addDataList(zhiHuLatestStory.getStories());
                    }
                });
    }


    private void loadingLatestStory() {
        retrofitRxjavaApi.getZhiHuLatestStory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhiHuLatestStory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ZhiHuLatestStory zhiHuLatestStory) {
                        System.out.print(zhiHuLatestStory.toString());
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        date = zhiHuLatestStory.getDate();
                        adapter.addDataList(zhiHuLatestStory.getStories());
                        images.clear();
                        images.addAll(zhiHuLatestStory.getTop_stories());
                        banner.update(zhiHuLatestStory.getTop_stories());
                    }
                });
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        loadingLatestStory();
    }


}
