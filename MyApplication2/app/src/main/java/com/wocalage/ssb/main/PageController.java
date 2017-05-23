package com.wocalage.ssb.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wocalage.ssb.config.Config;
import com.wocalage.ssb.guide.LoadingPage;
import com.wocalage.ssb.homepage.HomePage;
import com.wocalage.ssb.rank.RankPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017-5-22.
 */

public class PageController implements View.OnClickListener{
    
    private FragmentActivity mActivity;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter mPageAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mBtHome,mBtRank;
    private View mBottomView;
    private LinearLayout mMainContainer;
    private RankPage mRankPage;
    private HomePage mHomePage;
    
    public PageController(FragmentActivity activity){
        mActivity = activity;
    }
    
    public void showLoadPage(){
        Intent intent = new Intent(mActivity,LoadingPage.class);
        mActivity.startActivityForResult(intent, Config.START_LOADING_REQUEST_KEY);
    }
    
    public void showVisitorPage(){
        mFragments = new ArrayList<>();
        mRankPage = new RankPage();
        mFragments.add(mRankPage);
        initAdapter();
        mRankPage.setListener(new RankPage.OnRankPageListener() {
            @Override
            public void onLogin(boolean isLoginOK) {
                if (isLoginOK){
                    removeAllPage();
                    showLoginedPage();
                }
            }
        });
    }
    
    public void showLoginedPage(){
        initTab();
        mFragments = new ArrayList<>();
        mRankPage = new RankPage();
        mHomePage = new HomePage();
        mFragments.add(mRankPage);
        mFragments.add(mHomePage);
        initAdapter();
        mRankPage.setListener(new RankPage.OnRankPageListener() {
            @Override
            public void onLogin(boolean isLoginOK) {

            }
        });
    }

    public void init(){
        mActivity.setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) mActivity.findViewById(R.id.ssb_main_view_container);
    }
    private void initTab(){
        mMainContainer = (LinearLayout) mActivity.findViewById(R.id.activity_main);
        mBottomView = mActivity.getLayoutInflater().inflate(R.layout.ssb_main_bottom_view,null);
        mMainContainer.addView(mBottomView);
        mBtHome = (RelativeLayout) mBottomView.findViewById(R.id.ssb_main_bottom_home);
        mBtRank = (RelativeLayout) mBottomView.findViewById(R.id.ssb_main_bottom_rank);
        mBtRank.setOnClickListener(this);
        mBtHome.setOnClickListener(this);

        //初始化选择rank
        setPageSelected(0);
    }

    private void initAdapter(){
        mPageAdapter  = new FragmentPagerAdapter(mActivity.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void removeAllPage(){
        mFragments.clear();
    }

    public void setPageSelected(int position){
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ssb_main_bottom_rank:
                setPageSelected(0);
                mBtRank.setSelected(true);
                mBtHome.setSelected(false);
                break;
            case R.id.ssb_main_bottom_home:
                setPageSelected(1);
                mBtRank.setSelected(false);
                mBtHome.setSelected(true);
                break;
        }
    }
}
