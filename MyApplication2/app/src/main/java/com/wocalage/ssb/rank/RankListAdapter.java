package com.wocalage.ssb.rank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wocalage.ssb.entity.UserInfo;
import com.wocalage.ssb.main.R;
import com.wocalage.ssb.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017/5/21.
 */

public class RankListAdapter extends RecyclerView.Adapter<RankListViewHolder> {

    private Context mContext;
    private List<UserInfo> mDatas = new ArrayList<>();

    public RankListAdapter(Context context, List<UserInfo> datas) {
        LogUtil.d(this,"RankListAdapter"+datas+"");
        mContext = context;
        mDatas = datas;
    }

    @Override
    public RankListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtil.d(this,"onCreateViewHolder"+mDatas);
        RankListViewHolder viewHolder = new RankListViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.ssb_rank_list_item_view, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RankListViewHolder holder, int position) {
        LogUtil.d(this,"onBindViewHolder"+mDatas+"");
        holder.mTVame.setText(mDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        LogUtil.d(this,"getItemCount"+mDatas+"");
        return mDatas.size();
    }
}