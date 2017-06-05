package com.wocalage.ssb.rank;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wocalage.ssb.main.R;

/**
 * Created by jiaojian on 2017/5/21.
 */

public class RankListViewHolder extends RecyclerView.ViewHolder {
    public RankListViewHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_username);
        mHead = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_head);
        mDes = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_des);
        mUp = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_up);
        mDown = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_down);
        mDistance = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_upDistance);
        mLikeNum = (TextView) itemView.findViewById(R.id.ssb_rank_list_item_likenum);
    }

    TextView mName;
    TextView mHead;
    TextView mDes;
    TextView mUp;
    TextView mDown;
    TextView mDistance;
    TextView mLikeNum;
}
