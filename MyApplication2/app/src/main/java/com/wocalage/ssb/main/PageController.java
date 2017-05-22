package com.wocalage.ssb.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017-5-22.
 */

public class PageController {
    
    private FragmentActivity mActivity;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter mPageAdapter;
    private ViewPager mViewPager;
    
    public PageController(FragmentActivity activity){
        mActivity = activity;
    }
    
    public void showLoadPage(){
        
    }
    
    public void showVisitorPage(){
        mFragments = new ArrayList<>();
    }
    
    public void showLoginedPage(){
        mFragments = new ArrayList<>();
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

    public void setPageSelected(int position){
        mViewPager.setCurrentItem(position);
    }
}
