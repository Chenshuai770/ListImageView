package com.cs.com.listimageview;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Create by Chenshuai
 * Date 2019/8/1/001 16:26
 * Descripton
 */
public class MultipleItem implements MultiItemEntity {
    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;
    public static final int TYPE3 = 3;
    public static final int TYPE4 = 4;
    public static final int TYPE5 = 5;
    private int itemType;
    private ImageBean.ResultsBean bean;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    public MultipleItem(int itemType, ImageBean.ResultsBean bean) {
        this.itemType = itemType;
        this.bean = bean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public ImageBean.ResultsBean getBean() {
        return bean;
    }

    public void setBean(ImageBean.ResultsBean bean) {
        this.bean = bean;
    }
}
