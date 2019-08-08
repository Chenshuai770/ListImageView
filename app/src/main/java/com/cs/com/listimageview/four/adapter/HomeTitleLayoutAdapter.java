package com.cs.com.listimageview.four.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.cs.com.listimageview.R;
import com.cs.com.listimageview.four.bean.HomeBean;

import java.util.List;

/**
 * Created by HaiyuKing
 * Used 通栏布局——轮播图
 */
public class HomeTitleLayoutAdapter extends DelegateAdapter.Adapter{

	private Context mContext;
	private LayoutHelper mLayoutHelper;
	private String mItemBeanList;
	private int mCount = 1;

	public HomeTitleLayoutAdapter(Context context, LayoutHelper layoutHelper, String datas){
		this.mContext = context;
		this.mLayoutHelper = layoutHelper;
		this.mItemBeanList = datas;
	}

	@Override
	public LayoutHelper onCreateLayoutHelper() {
		return mLayoutHelper;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new BannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.vlayout_adapter_item_title, parent, false));
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;
		((BannerViewHolder) holder).mEtTitle.setHint(mItemBeanList);
		
	}


	@Override
	public int getItemCount() {
		return mCount;
	}

	static class BannerViewHolder extends RecyclerView.ViewHolder{

		private TextView mEtTitle;

		public BannerViewHolder(View itemView) {
			super(itemView);
			mEtTitle = (TextView) itemView.findViewById(R.id.et_home_title);
		}
	}

	/**轮播图点击回调*/
	public interface BannerCallback{
		void clickBanner(String itemBean);
	}

	private BannerCallback bannerCallback;

	public void setBannerCallback(BannerCallback bannerCallback){
		this.bannerCallback = bannerCallback;
	}
}
