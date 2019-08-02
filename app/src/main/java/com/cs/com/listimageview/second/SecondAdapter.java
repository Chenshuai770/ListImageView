package com.cs.com.listimageview.second;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.cs.com.listimageview.R;

/**
 * Create by Chenshuai
 * Date 2019/8/2/002 10:09
 * Descripton
 */
public class SecondAdapter extends DelegateAdapter.Adapter<SecondAdapter.MainViewHolder> {


    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;

    public SecondAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), count);
    }


    public SecondAdapter(Context context, LayoutHelper layoutHelper, RecyclerView.LayoutParams layoutParams, int count) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.layoutParams = layoutParams;
        this.count = count;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.list_second_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        holder.tv1.setText(position+"");
        if (position > 7) {
            holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
        } else if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaa22ff22);
        } else {
            holder.itemView.setBackgroundColor(0xccff22ff);
        }

    }

    @Override
    public int getItemCount() {
        return count;
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1;

        public MainViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_second_title);
        }
    }
}
