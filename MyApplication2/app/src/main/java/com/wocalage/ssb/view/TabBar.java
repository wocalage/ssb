package com.wocalage.ssb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wocalage.ssb.main.R;

/**
 * Created by jiaojian on 2017/5/24.
 */

public class TabBar extends LinearLayout {

    private View mLeftView, mRightView;
    private TextView mLeftText, mRightText;
    private onTabBarListener mListener;

    public TabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabBar(Context context) {
        super(context);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.ssb_main_bottom_view,this);
        mRightView = findViewById(R.id.ssb_main_bottom_right);
        mLeftView = findViewById(R.id.ssb_main_bottom_left);
        mRightText = (TextView) findViewById(R.id.ssb_main_bottom_right_text);
        mLeftText = (TextView) findViewById(R.id.ssb_main_bottom_left_text);

        initEvent();
    }

    private void initEvent(){
        mRightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                   mListener.onRightChoosed();
                }
                setTabChoosed(1);
            }
        });
        mLeftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onLeftChoosed();
                }
                setTabChoosed(0);
            }
        });
    }

    public void setRightText(String str){
        mRightText.setText(str);
    }

    public void setLeftText(String str){
        mLeftText.setText(str);
    }

    public void setTabChoosed(int tabNum){
        if (tabNum == 0){
            mRightView.setSelected(true);
            mRightText.setTextColor(getResources().getColor(R.color.app_theme_color_opposite));
            mLeftView.setSelected(false);
            mLeftText.setTextColor(getResources().getColor(R.color.app_theme_color));
        }else{
            mRightView.setSelected(false);
            mRightText.setTextColor(getResources().getColor(R.color.app_theme_color));
            mLeftView.setSelected(true);
            mLeftText.setTextColor(getResources().getColor(R.color.app_theme_color_opposite));
        }
    }

    public interface onTabBarListener {
        void onLeftChoosed();
        void onRightChoosed();
    }
    public void setListener(onTabBarListener listener){
        mListener = listener;
    }
}
