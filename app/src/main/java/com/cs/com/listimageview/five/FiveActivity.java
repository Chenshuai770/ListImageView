package com.cs.com.listimageview.five;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgeAnchor;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgeRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FiveActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLogin;
    private RecyclerView mRlvFive;
    private MagicIndicator mMagicIndicator6;
    private ViewPager mViewpager;
    private List<Fragment> pageList;
    private List<String> titleList;
    private BaseViewPageAdapter pageAdapter;
    private RecyclerView.RecycledViewPool viewPool;
    private DelegateAdapter delegateAdapter;
    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

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
        for (int i = 0; i < mDataList.size(); i++) {
            pageList.add(new PageFragment());
        }


    }

    private void initView() {
        mLogin = (Button) findViewById(R.id.login);
        mRlvFive = (RecyclerView) findViewById(R.id.recy);
        mMagicIndicator6 = (MagicIndicator) findViewById(R.id.magic_indicator3);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);


        ItemAdapter3 itemAdapter = new ItemAdapter3(this);

        mRlvFive.setLayoutManager(new LinearLayoutManager(this));

        mRlvFive.setAdapter(itemAdapter);
        
        

        pageAdapter = new BaseViewPageAdapter(mDataList,pageList,getSupportFragmentManager());
        mViewpager.setAdapter(pageAdapter);
        mLogin.setOnClickListener(this);
        initMagic6();
    }

    private void initMagic6() {
        mMagicIndicator6.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                final BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);

                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));

                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewpager.setCurrentItem(index);
                        badgePagerTitleView.setBadgeView(null); // cancel badge when click tab
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);

                // setup badge
                if (index == 1) {
                    TextView badgeTextView = (TextView) LayoutInflater.from(context).inflate(R.layout.simple_count_badge_layout, null);
                    badgeTextView.setText("热门");
                    badgePagerTitleView.setBadgeView(badgeTextView);
                }

                // set badge position
                if (index == 1) {
                    badgePagerTitleView.setXBadgeRule(new BadgeRule(BadgeAnchor.CENTER_X, -UIUtil.dip2px(context, 15)));
                    badgePagerTitleView.setYBadgeRule(new BadgeRule(BadgeAnchor.CONTENT_TOP, -UIUtil.dip2px(context,10)));
                }
                // don't cancel badge when tab selected
                badgePagerTitleView.setAutoCancelBadge(false);

                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(Color.parseColor("#ff4a42"), Color.parseColor("#fcde64"), Color.parseColor("#73e8f4"), Color.parseColor("#76b0ff"), Color.parseColor("#c683fe"));
                return indicator;
            }
        });
        mMagicIndicator6.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator6, mViewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:

                break;
        }
    }


}
