package com.cs.com.listimageview.five;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Create by Chenshuai
 * Date 2019/8/16/016 16:13
 * Descripton
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public abstract class BaseLazyFragment extends Fragment {

    protected boolean isLazyLoad;//懒加载过，保证只加载一次
    private boolean isPrepared;//防止没有inflateView以及findView之前操作View导致空指针异常


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        layzLoad();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;

        layzLoad(); ////必须调用，否则首次打开会显示空白界面
    }

    protected void layzLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoad) {
            initData();//调用懒加载
            isLazyLoad = true;
        }
    }

    public abstract void initData();
}
