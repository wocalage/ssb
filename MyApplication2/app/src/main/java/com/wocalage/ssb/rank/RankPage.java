package com.wocalage.ssb.rank;

import android.content.Context;
import android.media.MediaDataSource;
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
import com.wocalage.ssb.view.TabBar;
import com.wocalage.ssb.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017/5/19.
 * rank page
 */

public class RankPage extends Fragment {

    private TitleBar mTitleBar;
    private TabBar mTabBar;
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
        mTabBar = (TabBar) me.findViewById(R.id.ssb_rank_tabbar);
        mRecyclerView = (RecyclerView) me.findViewById(R.id.ssb_rank_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mTabBar.setLeftText(getString(R.string.ssb_rank_tab_recent));
        mTabBar.setRightText(getString(R.string.ssb_rank_tab_total));

        // TODO: 2017-5-29 fetch the data
        mAdapter = new RankListAdapter(mContext);
        mTabBar.setTabChoosed(0);
        mAdapter.setData(getData1());
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
        mTabBar.setListener(new TabBar.onTabBarListener() {
            @Override
            public void onLeftChoosed() {
                // TODO: 2017-5-29 fetch recent data
                mAdapter.setData(getData1());
            }

            @Override
            public void onRightChoosed() {
                // TODO: 2017-5-29 fetch total data
                mAdapter.setData(getData2());
            }
        });
    }

    private List<UserInfo> getData1(){
        List<UserInfo> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserInfo user = new UserInfo();
            user.setName("张三"+i);
            user.setHead(""+i);
            datas.add(user);
        }
        return datas;
    }
    private List<UserInfo> getData2(){
        List<UserInfo> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            UserInfo user = new UserInfo();
            user.setName("李四"+i);
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
