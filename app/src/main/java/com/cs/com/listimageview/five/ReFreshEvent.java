package com.cs.com.listimageview.five;

/**
 * Create by Chenshuai
 * Date 2019/8/16/016 17:43
 * Descripton
 */
public class ReFreshEvent {
    private boolean isRefresh;

    public ReFreshEvent(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }
}
