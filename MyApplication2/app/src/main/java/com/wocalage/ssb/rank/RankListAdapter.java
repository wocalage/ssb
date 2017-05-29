package com.wocalage.ssb.rank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wocalage.ssb.entity.UserInfo;
import com.wocalage.ssb.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaojian on 2017/5/21.
 */

public class RankListAdapter extends RecyclerView.Adapter<RankListViewHolder> {

    private Context mContext;
    private List<UserInfo> mDatas = new ArrayList<>();

    public RankListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<UserInfo> datas){
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RankListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RankListViewHolder viewHolder = new RankListViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.ssb_rank_list_item_view, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RankListViewHolder holder, int position) {
        holder.mName.setText(mDatas.get(position).getName());
        holder.mHead.setText(mDatas.get(position).getHead());
        holder.mDistance.setText(mDatas.get(position).getUpDistance());
        if (mDatas.get(position).getUpDistance() > 0){
            holder.mUp.setVisibility(View.VISIBLE);
            holder.mUp.setVisibility(View.GONE);
        }else{
            holder.mUp.setVisibility(View.GONE);
            holder.mUp.setVisibility(View.VISIBLE);
        }
        holder.mLikeNum.setText(mDatas.get(position).getLikeNum());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
