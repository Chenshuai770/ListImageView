package com.cs.com.listimageview.five;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs.com.listimageview.R;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Pan on 2019/7/30.
 * Desc:
 */
public class PageFragment extends BaseLazyFragment {

    private View rootView;
    private RecyclerView mFRecy;
    private SmartRefreshLayout mRefreshLayout;
    private MaterialHeader mMaterialHeader;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_page, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {


        RecyclerView recyclerView = rootView.findViewById(R.id.f_recy);
        ItemAdapter2 adapter = new ItemAdapter2(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        mRefreshLayout = (SmartRefreshLayout) rootView.findViewById(R.id.srL_five);
        mMaterialHeader = (MaterialHeader)mRefreshLayout.getRefreshHeader();
        mRefreshLayout.setEnableHeaderTranslationContent(false);//内容不偏移
        mMaterialHeader.setShowBezierWave(false);//关闭背景


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ReFreshEvent(ReFreshEvent event) {

        if (event.isRefresh()) {
            mRefreshLayout.autoRefresh();
            Toast.makeText(getContext(), "我收1", Toast.LENGTH_SHORT).show();
            //mRefreshLayout.autoRefresh();

        }
    };


    @Override
    public void initData() {
        mRefreshLayout.autoRefresh();//自动刷新

        //setThemeColor(R.color.colorPrimary, R.color.colorPrimaryDark);
    }
}
