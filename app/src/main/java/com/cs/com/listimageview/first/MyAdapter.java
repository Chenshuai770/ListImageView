package com.cs.com.listimageview.first;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cs.com.listimageview.R;

import java.util.List;

/**
 * Create by Chenshuai
 * Date 2019/8/1/001 13:03
 * Descripton
 */
public class MyAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TYPE1, R.layout.item_type1);
        addItemType(MultipleItem.TYPE2, R.layout.item_type2);
        addItemType(MultipleItem.TYPE3, R.layout.item_type3);
        addItemType(MultipleItem.TYPE4, R.layout.item_type4);
        addItemType(MultipleItem.TYPE5, R.layout.item_type5);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultipleItem item) {
        List<String> url = item.getBean().getUrl();
        switch (helper.getItemViewType()) {
            case MultipleItem.TYPE1:

                Glide.with(mContext).load(url.get(0)).into((ImageView) helper.getView(R.id.iv_type1_item1));
                helper.setText(R.id.tv_type1_text,item.getBean().getDesc());

                break;
            case MultipleItem.TYPE2:
                Glide.with(mContext).load(url.get(0)).into((ImageView) helper.getView(R.id.iv_type2_item1));
                Glide.with(mContext).load(url.get(1)).into((ImageView) helper.getView(R.id.iv_type2_item2));


                break;
            case MultipleItem.TYPE3:
                Glide.with(mContext).load(url.get(0)).into((ImageView) helper.getView(R.id.iv_type3_item1));
                Glide.with(mContext).load(url.get(1)).into((ImageView) helper.getView(R.id.iv_type3_item2));
                Glide.with(mContext).load(url.get(2)).into((ImageView) helper.getView(R.id.iv_type3_item3));


                break;
            case MultipleItem.TYPE4:
                Glide.with(mContext).load(url.get(0)).into((ImageView) helper.getView(R.id.iv_type4_item1));
                Glide.with(mContext).load(url.get(1)).into((ImageView) helper.getView(R.id.iv_type4_item2));
                Glide.with(mContext).load(url.get(2)).into((ImageView) helper.getView(R.id.iv_type4_item3));
                Glide.with(mContext).load(url.get(3)).into((ImageView) helper.getView(R.id.iv_type4_item4));


                break;
            case MultipleItem.TYPE5:
                Glide.with(mContext).load(url.get(0)).into((ImageView) helper.getView(R.id.iv_type5_item1));
                Glide.with(mContext).load(url.get(1)).into((ImageView) helper.getView(R.id.iv_type5_item2));
                Glide.with(mContext).load(url.get(2)).into((ImageView) helper.getView(R.id.iv_type5_item3));
                Glide.with(mContext).load(url.get(3)).into((ImageView) helper.getView(R.id.iv_type5_item4));
                Glide.with(mContext).load(url.get(4)).into((ImageView) helper.getView(R.id.iv_type5_item5));


                break;
        }
    }
}
