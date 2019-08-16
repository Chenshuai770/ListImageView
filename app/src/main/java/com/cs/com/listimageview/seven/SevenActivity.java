package com.cs.com.listimageview.seven;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cs.com.listimageview.R;
import com.cs.com.listimageview.five.ItemAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

public class SevenActivity extends AppCompatActivity {

    private RecyclerView mRlvSeven;
    private SmartRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_seven);

        initView();
    }


    private void initView() {
        mRlvSeven = (RecyclerView) findViewById(R.id.rlv_seven);
        ItemAdapter itemAdapter = new ItemAdapter(this);
        mRlvSeven.setLayoutManager(new LinearLayoutManager(this));
        mRlvSeven.setAdapter(itemAdapter);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {

                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        refreshLayout.resetNoMoreData();
                    }
                }, 1000);
            }
        });

        //点击测试
        RefreshFooter footer = mRefreshLayout.getRefreshFooter();
        if (footer instanceof ClassicsFooter) {
            mRefreshLayout.getRefreshFooter().getView().findViewById(ClassicsFooter.ID_TEXT_TITLE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "点击测试", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
