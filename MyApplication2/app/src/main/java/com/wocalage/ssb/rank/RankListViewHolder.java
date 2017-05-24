package com.wocalage.ssb.rank;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.wocalage.ssb.main.R;

/**
 * Created by jiaojian on 2017/5/21.
 */

public class RankListViewHolder extends RecyclerView.ViewHolder {
    public RankListViewHolder(View itemView) {
        super(itemView);
        mTVame = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_username);
        mHead = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_head);
    }

    TextView mTVame;
    TextView mHead;
}
