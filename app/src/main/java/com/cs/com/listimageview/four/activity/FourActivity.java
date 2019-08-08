package com.cs.com.listimageview.four.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.blankj.utilcode.util.ConvertUtils;
import com.cs.com.listimageview.R;
import com.cs.com.listimageview.first.MainActivity;
import com.cs.com.listimageview.four.adapter.BannerLayoutAdapter;
import com.cs.com.listimageview.four.adapter.HomeTitleLayoutAdapter;
import com.cs.com.listimageview.four.bean.HomeBean;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FourActivity extends AppCompatActivity {

    private RecyclerView mRlvFour;
    private RecyclerView.RecycledViewPool viewPool;
    private DelegateAdapter delegateAdapter;
    private int itemType;//一个Adapter对应一个类型，这里通过自增加1实现唯一性
    private LinkedList<DelegateAdapter.Adapter> adapters;
    private List<HomeBean.DataBeanX> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        initView();
        initData();
    }

    private void initData() {
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        getTestDatas();
        layoutManager.setRecycleOffset(300);
        mRlvFour.setLayoutManager(layoutManager);
        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        //针对type=0的item设置了复用池的大小，如果你的页面有多种type，需要为每一种类型的分别调整复用池大小参数。
        viewPool = new RecyclerView.RecycledViewPool();
        mRlvFour.setRecycledViewPool(viewPool);
        //加载数据，通过创建adapter集合实现布局
        //必须使用false，实现每一个分组的类型不同,会卡顿很多
        delegateAdapter = new DelegateAdapter(layoutManager, false);
        mRlvFour.setAdapter(delegateAdapter);
        setLayoutAdapter();
    }

    private void initView() {
        mRlvFour = (RecyclerView) findViewById(R.id.rlv_four);


    }

    private void setLayoutAdapter() {
        itemType = 0;//自增加1
        if (adapters != null) {
            adapters.clear();
        } else {
            adapters = new LinkedList<>();
        }

        int stickyPos1=0;
        int stickyPos2=0;

        for (int i = 0; i < mData.size(); i++) {
            List<HomeBean.DataBeanX.DataBean> beanList = mData.get(i).getData();
            String type = mData.get(i).getType();


            switch (type) {
                case "banner":
                    //通栏布局——轮播图
                    //设置各个区域的复用池的大小，设置子集合的总个数为复用池大小

                    viewPool.setMaxRecycledViews(itemType++, beanList.size());

                    Log.d("TTT", itemType + "\t" + "beanList.size():" + beanList.size());
                    stickyPos1=beanList.size();
                    SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();//通栏布局，只会显示一个组件View
                    singleLayoutHelper.setMargin(0, 0, 0, ConvertUtils.dp2px(1));//设置外边距，实现分割效果
                    BannerLayoutAdapter bannerLayoutAdapter = new BannerLayoutAdapter(this, singleLayoutHelper, beanList);
                    bannerLayoutAdapter.setBannerCallback(new BannerLayoutAdapter.BannerCallback() {
                        @Override
                        public void clickBanner(HomeBean.DataBeanX.DataBean itemBean) {
                            Toast.makeText(FourActivity.this, itemBean.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });//设置自定义回调，用于点击事件监听
                    adapters.add(bannerLayoutAdapter);
                    break;

            }
            if (i==stickyPos1){
                //stikcy布局， 可以配置吸顶或者吸底
                //设置各个区域的复用池的大小，因为只有一个元素，所以复用池大小就设置为1
                viewPool.setMaxRecycledViews(itemType++, 1);
                Log.d("TTT", "itemType:" + itemType);
                StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
                HomeTitleLayoutAdapter stickyTitleAdapter = new HomeTitleLayoutAdapter(this, stickyLayoutHelper, "我是高手");
                adapters.add(stickyTitleAdapter);
            }

        }
        delegateAdapter.setAdapters(adapters);
    }

    /**
     * 访问assets目录下的资源文件，获取文件中的字符串
     *
     * @param assetsFilePath - 文件的相对路径，例如："listitemdata.txt或者"/why/listdata.txt"
     * @return 内容字符串
     */
    public static String getStringFromAssert(Context mContext, String assetsFilePath) {

        String content = ""; // 结果字符串
        try {
            InputStream is = mContext.getResources().getAssets().open(assetsFilePath);// 打开文件
            int ch = 0;
            ByteArrayOutputStream out = new ByteArrayOutputStream(); // 实现了一个输出流
            while ((ch = is.read()) != -1) {
                out.write(ch); // 将指定的字节写入此 byte 数组输出流
            }
            byte[] buff = out.toByteArray();// 以 byte 数组的形式返回此输出流的当前内容
            out.close(); // 关闭流
            is.close(); // 关闭流
            content = new String(buff, "UTF-8"); // 设置字符串编码
        } catch (Exception e) {
            Toast.makeText(mContext, "对不起，没有找到指定文件！", Toast.LENGTH_SHORT)
                    .show();
        }
        return content;
    }

    public void getTestDatas() {

        String json = getStringFromAssert(FourActivity.this, "vlayout.txt");
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(json, HomeBean.class);
        mData.clear();
        mData.addAll(homeBean.getData());


    }
}
