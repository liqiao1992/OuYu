package com.yizhan.ouyu.ui.fragment;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yizhan.ouyu.R;
import com.yizhan.ouyu.base.BaseFragment;

import java.io.IOException;

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
    private Runnable seekProgress = new Runnable() {
        @Override
        public void run() {
            int currentTime = (int) iMediaPlayer.getCurrentPosition() / 1000;
            seekBar.setProgress(currentTime);
            handler.postDelayed(this, 1000);
        }
    };

   private Runnable controlViewRunnable=new Runnable() {
       @Override
       public void run() {
           if(controlRelativeLayout.getVisibility()==View.VISIBLE){
               controlRelativeLayout.setVisibility(View.GONE);
           }
       }
   };

    public static KaiYanPlayFragment newInstance(String url, int duration) {
        KaiYanPlayFragment fragment = new KaiYanPlayFragment();
        Bundle bundle = new Bundle();
        bundle.putString("playUrl", url);
        bundle.putInt("duration", duration);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        if (data != null) {
            playUrl = data.getString("playUrl");
            videoDuration = data.getInt("duration");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kaiyan_play, null);
        initUi(rootView);
        return rootView;
    }

    private void initUi(View view) {
        videoFrameLayout= (FrameLayout) view.findViewById(R.id.fragment_kaiyan_play_video_frameLayout);
        videoFrameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacks(controlViewRunnable);
                        controlRelativeLayout.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("fuck","手指抬起来了。。。。。。。。。。。。。。。");
                        handler.postDelayed(controlViewRunnable,1000*20);
                        break;
                }
                return true;
            }
        });
        controlRelativeLayout= (RelativeLayout) view.findViewById(R.id.fragment_kaiyan_play_control_relativeLayout);
        progressBar = (ProgressBar) view.findViewById(R.id.fragment_kaiyan_play_progressBar);
        textureView = (TextureView) view.findViewById(R.id.fragment_kaiyan_play_textureTextView);
        textureView.setSurfaceTextureListener(this);
        seekBar = (SeekBar) view.findViewById(R.id.fragment_kaiyan_play_seekBar);
        imageButtonPlay = (ImageButton) view.findViewById(R.id.fragment_kaiyan_play_imageView_start);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iMediaPlayer.isPlaying()) {
                    iMediaPlayer.pause();
                    imageButtonPlay.setSelected(true);
                }else {
                    iMediaPlayer.start();
                    imageButtonPlay.setSelected(false);
                }
            }
        });

    }

    private void initData() {

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
        seekBar.setSecondaryProgress(i);
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        Log.i("fuck", "mediaPlayer play onPrepared....................." );
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
        switch (what){
            case IjkMediaPlayer.MEDIA_INFO_BUFFERING_START:
                progressBar.setVisibility(View.VISIBLE);
                break;
            case IjkMediaPlayer.MEDIA_INFO_BUFFERING_END:
                progressBar.setVisibility(View.GONE);
                break;
            case IjkMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
            case IjkMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                if(progressBar.getVisibility()==View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
                break;
        }
        return true;
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }
}
