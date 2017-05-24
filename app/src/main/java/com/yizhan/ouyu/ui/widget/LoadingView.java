package com.yizhan.ouyu.ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lenovo on 2017/5/23.
 */

public class LoadingView extends View {
    private Paint mPaint;
    private Path path_circle;//放大镜和外圈
    private PathMeasure mPathMeasure;
    private ValueAnimator valueAnimator;
    private float mAnimatorValue = 1;
    private float mViewWidth, mViewHeight;

    public LoadingView(Context context) {
        super(context);
        initPaint();
        initPath();
        initAnimator();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initPath();
        initAnimator();
    }

    private void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initPath(){
        path_circle=new Path();
        RectF rectF1 = new RectF(0, 0, 100, 100);
        path_circle.addArc(rectF1, 45, 359.9f);
        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(path_circle, false);
    }

    private void initAnimator(){
        valueAnimator= ValueAnimator.ofFloat(1,0).setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue= (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth=w;
        mViewHeight=h;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path=new Path();
        float stop = mPathMeasure.getLength() * mAnimatorValue;
        float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
        mPathMeasure.getSegment(start,stop,path,true);
        Log.i("fuck","stop:"+stop+" start:"+start);
        canvas.drawPath(path,mPaint);
    }
}
