package com.wk.materialdemo;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * User: WangKai(123940232@qq.com)
 * 2016-02-17 16:23
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private List<String> mList;

    public RvAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("way", "onBindViewHolder: " + position);
        holder.mTextView.setText(mList.get(position));
        holder.mTextView.setTextColor(Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
