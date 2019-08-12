package com.cs.com.listimageview.five;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.blankj.utilcode.util.ConvertUtils;
import com.cs.com.listimageview.R;
import com.cs.com.listimageview.four.adapter.SubAdapter;
import com.cs.com.listimageview.second.SecondAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FiveActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLogin;
    private RecyclerView mRlvFive;
    private TabLayout mTablayout;
    private ViewPager mViewpager;
    private List<Fragment> pageList;
    private List<String> titleList;
    private BaseViewPageAdapter pageAdapter;
    private RecyclerView.RecycledViewPool viewPool;
    private DelegateAdapter delegateAdapter;

    private static final boolean FLOAT_LAYOUT = true;
    private static final boolean BANNER_LAYOUT = true;

    private static final boolean LINEAR_LAYOUT = true;

    private static final boolean ONEN_LAYOUT = true;

    private static final boolean GRID_LAYOUT = true;

    private static final boolean STICKY_LAYOUT = true;

    private static final boolean HORIZONTAL_SCROLL_LAYOUT = true;

    private static final boolean SCROLL_FIX_LAYOUT = true;
    private TextView mFirstText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        initData();
        initView();

    }

    private void initData() {
        titleList = new ArrayList<>();
        pageList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("分类1");
        titleList.add("分类2");
        titleList.add("分类3");
        titleList.add("分类4");
        for (int i = 0; i < titleList.size(); i++) {
            pageList.add(new PageFragment());
        }


    }

    private void initView() {
        mLogin = (Button) findViewById(R.id.login);
        mRlvFive = (RecyclerView) findViewById(R.id.recy);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);


        ItemAdapter3 itemAdapter = new ItemAdapter3(this);

        mRlvFive.setLayoutManager(new LinearLayoutManager(this));

        mRlvFive.setAdapter(itemAdapter);
        
        

        pageAdapter = new BaseViewPageAdapter(titleList,pageList,getSupportFragmentManager());
        mTablayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(pageAdapter);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:

                break;
        }
    }


}
