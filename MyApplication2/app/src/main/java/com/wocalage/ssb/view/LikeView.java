package com.wocalage.ssb.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by jiaojian on 2017-5-29.
 */

public class LikeView extends View{

    private Paint paint;
    private float rate = 5; // 半径变化率
    private AnimThread at; // 改变rate的线程
    private Path path; // 路径

    public LikeView(Context context) {
        super(context);
        init();
    }

    public LikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        at = new AnimThread();
        // 初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        // 创建一个路径
        path = new Path();
        at.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 重置画板
        path.reset();
        // 得到屏幕的长宽的一半
        int px = getMeasuredWidth() / 2;
        int py = getMeasuredHeight() / 2;
        // 路径的起始点
        path.moveTo(px, py - 5 * rate);
        // 根据心形函数画图
        for (double i = 0; i <= 2 * Math.PI; i += 0.001) {
            float x = (float) (16 * Math.sin(i) * Math.sin(i) * Math.sin(i));
            float y = (float) (13 * Math.cos(i) - 5 * Math.cos(2 * i) - 2 * Math.cos(3 * i) - Math.cos(4 * i));
            x *= rate;
            y *= rate;
            x = px - x;
            y = py - y;
            path.lineTo(x, y);
        }
        canvas.drawPath(path, paint);
    }

    private class AnimThread extends Thread {
        public void run() {
            while (true) {
                rate += 0.05;
                if (rate > 20) { // 我的手机大于20后就很大了，为了不超过屏幕
                    rate = 5;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 刷新画布
                postInvalidate();
            }
        }
    };
}
