package com.cs.com.listimageview.second;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.cs.com.listimageview.R;

import java.util.LinkedList;

public class SecondActivity extends AppCompatActivity {



    private String url1 = "http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg";
    private String url2 = "https://ww1.sinaimg.cn/large/0065oQSqly1g2hekfwnd7j30sg0x4djy.jpg";
    private String url3 = "https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg";
    private String url4 = "https://ws1.sinaimg.cn/large/0065oQSqly1fytdr77urlj30sg10najf.jpg";
    private String url5 = "https://ws1.sinaimg.cn/large/0065oQSqly1fymj13tnjmj30r60zf79k.jpg";
    private RecyclerView mRlSecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {

        //setLinearLayout();
        //setGridLayout();
        //StaggeredGridLayout();



    }

    //设置瀑布流
    private void StaggeredGridLayout() {
        mRlSecond = (RecyclerView) findViewById(R.id.rl_second);
        //设置阿里的虚拟布局管理器
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRlSecond.setLayoutManager(manager);
        StaggeredGridLayoutHelper staggeredGridLayout = new StaggeredGridLayoutHelper(4,20);


        LinkedList<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        adapters.add(new SecondAdapter(this,staggeredGridLayout,60){
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //为了做成瀑布流的效果，我们对每个item进行一个随机高度的设置：
                ViewGroup.LayoutParams layoutParams = ((MainViewHolder) holder).tv1.getLayoutParams();
                layoutParams.height = 260 + position % 7 * 20;
                ((MainViewHolder) holder).tv1.setLayoutParams(layoutParams);
                ((MainViewHolder) holder).tv1.setText(position + 1 + "");

            }
        });
        DelegateAdapter delegateAdapter = new DelegateAdapter(manager);
        delegateAdapter.setAdapters(adapters);
        mRlSecond.setAdapter(delegateAdapter);
    }

    //设置Grid布局
    private void setGridLayout() {
        mRlSecond = (RecyclerView) findViewById(R.id.rl_second);
        //设置阿里的虚拟布局管理器
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRlSecond.setLayoutManager(manager);
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        //gridLayoutHelper.setItemCount(5);//etItemCount()方法设置的Item数量如果与Adapter的getItemCount()方法返回的数量不同，会取决于后者

        gridLayoutHelper.setMargin(ConvertUtils.px2dp(20),ConvertUtils.px2dp(20),ConvertUtils.px2dp(20),ConvertUtils.px2dp(20));

        gridLayoutHelper.setAutoExpand(false);

        LinkedList<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        adapters.add(new SecondAdapter(this,gridLayoutHelper,7){
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position==0) {
                    holder.tv1.setText("linerLayout");
                }
            }
        });
        DelegateAdapter delegateAdapter = new DelegateAdapter(manager);
        delegateAdapter.setAdapters(adapters);
        mRlSecond.setAdapter(delegateAdapter);
    }

    //设置线性布局
    private void setLinearLayout() {
        mRlSecond = (RecyclerView) findViewById(R.id.rl_second);
        //设置阿里的虚拟布局管理器
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRlSecond.setLayoutManager(manager);
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        //linearLayoutHelper.setItemCount(5);//etItemCount()方法设置的Item数量如果与Adapter的getItemCount()方法返回的数量不同，会取决于后者

        linearLayoutHelper.setMargin(ConvertUtils.px2dp(20),0,ConvertUtils.px2dp(20),0);
        linearLayoutHelper.setDividerHeight(ConvertUtils.px2dp(20));

        LinkedList<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        adapters.add(new SecondAdapter(this,linearLayoutHelper,7){
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position==0) {
                    holder.tv1.setText("linerLayout");
                }
            }
        });
        DelegateAdapter delegateAdapter = new DelegateAdapter(manager);
        delegateAdapter.setAdapters(adapters);
        mRlSecond.setAdapter(delegateAdapter);
    }


}
