package com.cs.com.listimageview.five;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cs.com.listimageview.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pan on 2019/7/30.
 * Desc:
 */
public class ItemAdapter3 extends RecyclerView.Adapter<ItemAdapter3.MyViewHolder> {

    private Context mContext;
    private List<Integer> images = new ArrayList<Integer>();
    public ItemAdapter3(Context context) {
        mContext = context;
        images.add(R.drawable.abc);
        images.add(R.drawable.cde);
        images.add(R.drawable.kkk);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext, R.layout.recy_item3,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //设置图片加载器
        holder.mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        holder.mBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        holder.mBanner.start();
        holder.mLlHeader1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "wodsfalsd", Toast.LENGTH_SHORT).show();
            }
        });

    }

  

    @Override
    public int getItemCount() {
        return 1;
    }

     public class MyViewHolder extends RecyclerView.ViewHolder {

        public Banner mBanner;
        public LinearLayout mLlHeader1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner_item_home);
            mLlHeader1 = itemView.findViewById(R.id.ll_item_home_header1);
        }
    }
}
