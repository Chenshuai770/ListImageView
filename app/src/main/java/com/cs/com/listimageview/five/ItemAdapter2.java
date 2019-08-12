package com.cs.com.listimageview.five;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs.com.listimageview.R;
import com.cs.com.listimageview.four.adapter.SubAdapter;


/**
 * Created by Pan on 2019/7/30.
 * Desc:
 */
public class ItemAdapter2 extends RecyclerView.Adapter<ItemAdapter2.MyViewHolder> {

    private Context mContext;

    public ItemAdapter2(Context context) {
        mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recy_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mTvTitle.setText(position+"");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

     class MyViewHolder extends RecyclerView.ViewHolder {

        public   TextView mTvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_item2_test);
        }
    }
}
