package com.yizhan.ouyu.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yizhan.ouyu.R;
import com.yizhan.ouyu.adapter.DribbbleFollowingFragmentAdapter;
import com.yizhan.ouyu.adapter.KaiYanPlayFragmentAdapter;
import com.yizhan.ouyu.api.RetrofitRxjavaService;
import com.yizhan.ouyu.base.BaseFragment;
import com.yizhan.ouyu.entity.KaiYanVideo;
import com.yizhan.ouyu.entity.KaiYanVideoList;

import java.io.IOException;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by lenovo on 2017/6/2.
 */

public class KaiYanPlayFragment extends BaseFragment implements TextureView.SurfaceTextureListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnSeekCompleteListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnVideoSizeChangedListener {

    private TextureView textureView;
    private IMediaPlayer iMediaPlayer;
    private Surface mSurface;
    private String playUrl;
    private SeekBar seekBar;
    private int videoDuration;
    private Handler handler = new Handler();
    private ImageButton imageButtonPlay;//用imagebutton 设置src属性会有阴影背景
    private ProgressBar progressBar;
    private RelativeLayout controlRelativeLayout;
    private FrameLayout videoFrameLayout;
    private KaiYanVideo mData;
    private ImageView coverImageView;
    private TextView currentTimeTv, durationTv;
    private TextView titleTv, descTv, categoryTv, timeTv, favoriteTv, shareTv, commentTv, downloadTv;
    private RecyclerView recyclerView;
    private KaiYanPlayFragmentAdapter adapter;
    private Runnable seekProgress = new Runnable() {
        @Override
        public void run() {
            int currentTime = (int) iMediaPlayer.getCurrentPosition() / 1000;
            if (currentTime <= 3600) {
                StringBuilder time = new StringBuilder();
                int second = currentTime % 60;
                int minute = currentTime / 60;
                if (minute < 10) {
                    time.append("0" + minute + ":");
                } else {
                    time.append(minute + ":");
                }
                if (second < 10) {
                    time.append("0" + second);
                } else {
                    time.append(second + "");
                }
                currentTimeTv.setText(time);
            }
            seekBar.setProgress(currentTime);
            handler.postDelayed(this, 1000);
        }
    };

    private Runnable controlViewRunnable = new Runnable() {
        @Override
        public void run() {
            if (controlRelativeLayout.getVisibility() == View.VISIBLE) {
                controlRelativeLayout.setVisibility(View.INVISIBLE);
            }
        }
    };

    public static KaiYanPlayFragment newInstance(KaiYanVideo data) {
        KaiYanPlayFragment fragment = new KaiYanPlayFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        if (data != null) {
            mData = data.getParcelable("data");
            playUrl = mData.getData().getPlayUrl();
            videoDuration = mData.getData().getDuration();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kaiyan_play, null);
        initUi(rootView);
        initData();
        return rootView;
    }

    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_kaiyan_play_recyclerView);
        videoFrameLayout = (FrameLayout) view.findViewById(R.id.fragment_kaiyan_play_video_frameLayout);
        videoFrameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacks(controlViewRunnable);
                        controlRelativeLayout.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("fuck", "手指抬起来了。。。。。。。。。。。。。。。");
                        handler.postDelayed(controlViewRunnable, 1000 * 20);
                        break;
                }
                return true;
            }
        });
        currentTimeTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_currentTime_textView);
        durationTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_duration_textView);

        StringBuilder time = new StringBuilder();
        if (videoDuration <= 3600) {
            int second = videoDuration % 60;
            int minute = videoDuration / 60;
            if (minute < 10) {
                time.append("0" + minute + ":");
            } else {
                time.append(minute + ":");
            }
            if (second < 10) {
                time.append("0" + second);
            } else {
                time.append(second + "");
            }
        }
        durationTv.setText(time);
        coverImageView = (ImageView) view.findViewById(R.id.fragment_kaiyan_play_cover_imageView);
        controlRelativeLayout = (RelativeLayout) view.findViewById(R.id.fragment_kaiyan_play_control_relativeLayout);
        progressBar = (ProgressBar) view.findViewById(R.id.fragment_kaiyan_play_progressBar);
        textureView = (TextureView) view.findViewById(R.id.fragment_kaiyan_play_textureTextView);
        textureView.setSurfaceTextureListener(this);
        seekBar = (SeekBar) view.findViewById(R.id.fragment_kaiyan_play_seekBar);
        imageButtonPlay = (ImageButton) view.findViewById(R.id.fragment_kaiyan_play_imageView_start);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iMediaPlayer.isPlaying()) {
                    iMediaPlayer.pause();
                    imageButtonPlay.setSelected(true);
                } else {
                    iMediaPlayer.start();
                    imageButtonPlay.setSelected(false);
                }
            }
        });

    }


    private void initData() {
        Glide.with(getContext())
                .load(mData.getData().getCover().getFeed())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(coverImageView);

        adapter = new KaiYanPlayFragmentAdapter(getContext());
        final View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_play_fragment_head_view, null);
        initHeadViewUi(headView);
        adapter.setHeaderView(headView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        SimpleTarget target=new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                recyclerView.setBackground(new BitmapDrawable(resource));
            }
        };
        Glide.with(getContext())
                .load(mData.getData().getCover().getBlurred())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new RotateTransformation(getContext(),90f))
                .into(target);
        loadRelatedVideo();
    }
    public class RotateTransformation extends BitmapTransformation {

        private float rotateRotationAngle = 0f;

        public RotateTransformation(Context context, float rotateRotationAngle) {
            super( context );

            this.rotateRotationAngle = rotateRotationAngle;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Matrix matrix = new Matrix();

            matrix.postRotate(rotateRotationAngle);

            return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
        }

        @Override
        public String getId() {
            return "rotate" + rotateRotationAngle;
        }
    }
    private void initHeadViewUi(View view) {
        titleTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_title_textView);
        titleTv.setText(mData.getData().getTitle());
        descTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_des_textView);
        descTv.setText(mData.getData().getDescription());
        categoryTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_category_textView);
        categoryTv.setText("#" + mData.getData().getCategory());
        timeTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_time_textView);
        StringBuilder stringBuilder = new StringBuilder();
        if (mData.getData().getDuration() <= 3600) {
            int second = mData.getData().getDuration() % 60;
            int minute = mData.getData().getDuration() / 60;
            stringBuilder.append(minute + "'");
            stringBuilder.append(second + "''");
        }
        timeTv.setText(stringBuilder);

        favoriteTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_favorite_textView);
        favoriteTv.setText(mData.getData().getConsumption().getCollectionCount() + "");
        shareTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_share_textView);
        shareTv.setText(mData.getData().getConsumption().getShareCount() + "");
        commentTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_comment_textView);
        commentTv.setText(mData.getData().getConsumption().getReplyCount() + "");
        downloadTv = (TextView) view.findViewById(R.id.fragment_kaiyan_play_download_textView);
    }

    private void loadRelatedVideo() {
        RetrofitRxjavaService.builder().KaiYanApi().getKaiYanRelatedVideo(mData.getData().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<KaiYanVideoList, List<KaiYanVideo>>() {

                    @Override
                    public List<KaiYanVideo> call(KaiYanVideoList kaiYanVideoList) {
                        return kaiYanVideoList.getItemList();
                    }
                }).subscribe(new Subscriber<List<KaiYanVideo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<KaiYanVideo> kaiYanVideos) {
                adapter.addDataList(kaiYanVideos);
            }
        });

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mSurface = new Surface(surface);
        seekBar.setMax(videoDuration);
        seekBar.setProgress(0);
        play(playUrl);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mSurface.release();
        mSurface = null;
        iMediaPlayer.stop();
        iMediaPlayer.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    private void play(String playUrl) {
        iMediaPlayer = new IjkMediaPlayer();
        iMediaPlayer.setSurface(mSurface);
        try {
            iMediaPlayer.setDataSource(playUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        iMediaPlayer.setOnCompletionListener(this);
        iMediaPlayer.setOnBufferingUpdateListener(this);
        iMediaPlayer.setScreenOnWhilePlaying(true);
        iMediaPlayer.setOnPreparedListener(this);
        iMediaPlayer.setOnSeekCompleteListener(this);
        iMediaPlayer.setOnErrorListener(this);
        iMediaPlayer.setOnInfoListener(this);
        iMediaPlayer.setOnVideoSizeChangedListener(this);
        iMediaPlayer.prepareAsync();

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        Log.i("fuck", "mediaPlayer play complete.....................");
        handler.removeCallbacks(seekProgress);
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
        Log.i("fuck", "mediaPlayer play bufferingUpdate:" + i);
        Log.i("fuck", "the duration of video:" + iMediaPlayer.getDuration());
        seekBar.setSecondaryProgress(i);
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        Log.i("fuck", "mediaPlayer play onPrepared.....................");
        iMediaPlayer.start();
        handler.post(seekProgress);
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        Log.i("fuck", "mediaPlayer play error.....................");
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int i1) {
        Log.i("fuck", "mediaPlayer play onInfo....................." + what + ",,,,,," + i1);
        switch (what) {
            case IjkMediaPlayer.MEDIA_INFO_BUFFERING_START:
                progressBar.setVisibility(View.VISIBLE);
                break;
            case IjkMediaPlayer.MEDIA_INFO_BUFFERING_END:
                coverImageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                break;
            case IjkMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
            case IjkMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
                coverImageView.setVisibility(View.GONE);
                break;
        }
        return true;
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }
}
