package com.cs.com.listimageview.four.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.cs.com.listimageview.R;
import com.cs.com.listimageview.four.view.ColorFlipPagerTitleView;
import com.cs.com.listimageview.three.ThreeActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HaiyuKing
 * Used 通栏布局——轮播图
 */
public class HomeNavigationAdapter extends DelegateAdapter.Adapter<HomeNavigationAdapter.NavigationViewHolder> {

    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private String mItemBeanList;
    private int mCount = 1;
    private List<String> mDataList;

    public HomeNavigationAdapter(Context mContext, LayoutHelper mLayoutHelper, String mItemBeanList) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mItemBeanList = mItemBeanList;
        String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"};
        mDataList = Arrays.asList(CHANNELS);

    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavigationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.vlayout_adapter_navigation, parent, false));
    }

    @Override
    public void onBindViewHolder(final NavigationViewHolder holder, int position) {
        holder.mViewPager.setAdapter(new ExamplePagerAdapter(mDataList));
        holder.magicIndicator.setBackgroundColor(Color.parseColor("#fafafa"));
        CommonNavigator commonNavigator7 = new CommonNavigator(mContext);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#9e9e9e"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#00c853"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#00c853"));
                return indicator;
            }
        });
        holder.magicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(holder.magicIndicator, holder.mViewPager);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }


    static class NavigationViewHolder extends RecyclerView.ViewHolder {

        public MagicIndicator magicIndicator;
        public ViewPager mViewPager;

        public NavigationViewHolder(View itemView) {
            super(itemView);
            magicIndicator = (MagicIndicator) itemView.findViewById(R.id.magic_indicator7);
            mViewPager = (ViewPager) itemView.findViewById(R.id.view_pager);

        }



    }

    class ExamplePagerAdapter extends PagerAdapter {
        private List<String> mDataList;

        public ExamplePagerAdapter(List<String> dataList) {
            mDataList = dataList;
        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setText(mDataList.get(position));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(24);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            TextView textView = (TextView) object;
            String text = textView.getText().toString();
            int index = mDataList.indexOf(text);
            if (index >= 0) {
                return index;
            }
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mDataList.get(position);
        }
    }

    /**
     * 轮播图点击回调
     */
    public interface BannerCallback {
        void clickBanner(String itemBean);
    }

    private BannerCallback bannerCallback;

    public void setBannerCallback(BannerCallback bannerCallback) {
        this.bannerCallback = bannerCallback;
    }
}
