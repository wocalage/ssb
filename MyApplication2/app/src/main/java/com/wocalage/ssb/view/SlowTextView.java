package com.wocalage.ssb.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.wocalage.ssb.util.DensityUtils;
import com.wocalage.ssb.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017-5-19.
 * a custom textview that words come out one by one
 */

public class SlowTextView extends TextView {

    private TextPaint mPaint;
    private int mTextColor;
    private float mTextSize;
    private int mCount = 0;
    private String mContents = "";
    private long time = 200;

    private List<String> mContentsList = new ArrayList<>();


    public SlowTextView(Context context) {
        super(context);
        init();
    }

    public SlowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlowTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setDelayPlayTime(long time) {
        this.time = time;
    }
    public void setContent(String content) {
        reset();
        LogUtil.i(this,content);
        char[] chars =content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            mContentsList.add(chars[i]+"");
        }
        invalidate();
    }
    private void init() {
        mTextColor = Color.BLACK;
        mTextSize = DensityUtils.sp2px(getContext(),16);

        mPaint = new TextPaint();
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        if (mCount >= mContentsList.size()) {
            return;
        }
        mContents += mContentsList.get(mCount);
        LogUtil.i(this,mContents);
        float baseline = getMeasuredHeight()/2 + mPaint.getTextSize()/2 - mPaint.getFontMetrics().descent;
        canvas.drawText(mContents,getMeasuredWidth()/2 , baseline ,mPaint);
        mCount++;
        startText();
    }

    public void startText() {
        if (mCount != mContentsList.size()) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            }, time);
        }
    }

    private void reset(){
        mContents = "";
        mContentsList.clear();
        mCount = 0;
    }
}
