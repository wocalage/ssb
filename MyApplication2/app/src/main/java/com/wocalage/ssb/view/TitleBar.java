package com.wocalage.ssb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wocalage.ssb.main.R;

/**
 * Created by jiaojian on 2017/5/21.
 */

public class TitleBar extends RelativeLayout {

    private TextView mTvTitle;
    private Button mBtQuestion;
    private TitleBarListener mListener;
    public TitleBar(Context context) {
        super(context);
        init();
    }
    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.ssb_rank_title_view,this);
        mTvTitle = (TextView) findViewById(R.id.ssb_loading_title);
        mBtQuestion = (Button) findViewById(R.id.ssb_rank_question);

        mBtQuestion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onQuestionClick();
                }
            }
        });
    }

    public void setTitle(String str){
        mTvTitle.setText(str);
    }

    public void setListener(TitleBarListener listener){
        mListener = listener;
    }

    public interface TitleBarListener{
        void onQuestionClick();
    }
}
