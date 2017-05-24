package com.wocalage.ssb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wocalage.ssb.main.R;

/**
 * Created by jiaojian on 2017/5/24.
 */

public class BottomBar extends LinearLayout {

    private View mRank,mHome;
    private TextView mRankText,mHomeText;
    private onBottomBarListener mListener;

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomBar(Context context) {
        super(context);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.ssb_main_bottom_view,this);
        mHome = findViewById(R.id.ssb_main_bottom_home);
        mRank = findViewById(R.id.ssb_main_bottom_rank);
        mHomeText = (TextView) findViewById(R.id.ssb_main_bottom_home_text);
        mRankText = (TextView) findViewById(R.id.ssb_main_bottom_rank_text);

        initEvent();
    }

    private void initEvent(){
        mHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                   mListener.onHomeChoosed();
                }
                setTabChoosed(1);
            }
        });
        mRank.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onRankChoosed();
                }
                setTabChoosed(0);
            }
        });
    }

    public void setTabChoosed(int tabNum){
        if (tabNum == 0){
            mHome.setSelected(true);
            mHomeText.setTextColor(getResources().getColor(R.color.app_theme_color_opposite));
            mRank.setSelected(false);
            mRankText.setTextColor(getResources().getColor(R.color.app_theme_color));
        }else{
            mHome.setSelected(false);
            mHomeText.setTextColor(getResources().getColor(R.color.app_theme_color));
            mRank.setSelected(true);
            mRankText.setTextColor(getResources().getColor(R.color.app_theme_color_opposite));
        }
    }

    public interface onBottomBarListener{
        void onRankChoosed();
        void onHomeChoosed();
    }
    public void setListener(onBottomBarListener listener){
        mListener = listener;
    }
}
