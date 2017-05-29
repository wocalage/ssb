package com.wocalage.ssb.rank;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wocalage.ssb.callback.SSBCallCack;
import com.wocalage.ssb.config.LoginInfo;
import com.wocalage.ssb.entity.UserInfo;
import com.wocalage.ssb.main.R;
import com.wocalage.ssb.manager.LoginManager;
import com.wocalage.ssb.manager.QuestionManager;
import com.wocalage.ssb.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017/5/19.
 * rank page
 */

public class RankPage extends Fragment {

    private TitleBar mTitleBar;
    private View me;
    private RecyclerView mRecyclerView;
    private RankListAdapter mAdapter;
    private Context mContext;
    private OnRankPageListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ssb_rank_page_view,container,false);
        me = view;
        initView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initView(){
        mTitleBar = (TitleBar) me.findViewById(R.id.ssb_rank_titlebar);
        mRecyclerView = (RecyclerView) me.findViewById(R.id.ssb_rank_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new RankListAdapter(mContext,getData());
        mRecyclerView.setAdapter(mAdapter);
        initEvent();
    }

    private void initEvent(){
        mTitleBar.setListener(new TitleBar.TitleBarListener() {
            @Override
            public void onQuestionClick() {
                if (LoginInfo.getInstance(mContext).isLogined()){
                    QuestionManager.getInstance().showHelp(mContext);
                }else{
                    LoginManager.getInstance().login(mContext, new SSBCallCack<UserInfo>() {
                        @Override
                        public void callBack(int code, String msg, UserInfo data) {
                            if (mListener != null){
                                mListener.onLogin((code == SSBCallCack.CODE_SUCCESS)?true:false);
                            }
                        }
                    });
                }
            }
        });
    }

    private List<UserInfo> getData(){
        List<UserInfo> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserInfo user = new UserInfo();
            user.setName("张三"+i);
            user.setHead(""+i);
            datas.add(user);
        }
        return datas;
    }

    public void setListener(OnRankPageListener listener){
        mListener = listener;
    }
    public interface OnRankPageListener{
        void onLogin(boolean isLoginOK);
    }

}
