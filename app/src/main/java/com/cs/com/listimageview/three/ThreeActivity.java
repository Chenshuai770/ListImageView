package com.cs.com.listimageview.three;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.blankj.utilcode.util.ConvertUtils;
import com.cs.com.listimageview.R;

import java.util.LinkedList;
import java.util.List;

public class ThreeActivity extends AppCompatActivity {

    private int height;

    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MainViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }

    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    static class PagerAdapter extends RecyclablePagerAdapter<MainViewHolder> {
        public PagerAdapter(SubAdapter adapter, RecyclerView.RecycledViewPool pool) {
            super(adapter, pool);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public void onBindViewHolder(MainViewHolder viewHolder, int position) {
            // only vertical
            viewHolder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((TextView) viewHolder.itemView.findViewById(R.id.title)).setText("Banner: " + position);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }
    }


    private static final boolean FLOAT_LAYOUT = true;
    private static final boolean BANNER_LAYOUT = true;

    private static final boolean LINEAR_LAYOUT = true;

    private static final boolean ONEN_LAYOUT = true;

    private static final boolean GRID_LAYOUT = true;

    private static final boolean STICKY_LAYOUT = true;

    private static final boolean HORIZONTAL_SCROLL_LAYOUT = true;

    private static final boolean SCROLL_FIX_LAYOUT = true;
    private TextView mFirstText;
    private TextView mLastText;

    private TextView mCountText;

    private TextView mTotalOffsetText;

    private Runnable trigger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_view);

        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText position = (EditText) findViewById(R.id.position);
                if (!TextUtils.isEmpty(position.getText())) {
                    try {
                        int pos = Integer.parseInt(position.getText().toString());
                        recyclerView.scrollToPosition(pos);
                    } catch (Exception e) {
                        Log.e("VlayoutActivity", e.getMessage(), e);
                    }
                } else {
                    recyclerView.requestLayout();
                }
            }
        });

        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
               /* mFirstText.setText("First: " + layoutManager.findFirstVisibleItemPosition());
                mLastText.setText("Existing: " + MainViewHolder.existing + " Created: " + MainViewHolder.createdTimes);
                mCountText.setText("Count: " + recyclerView.getChildCount());
                mTotalOffsetText.setText("Total Offset: " + layoutManager.getOffsetToStart());*/
            }
        });


        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        recyclerView.setRecycledViewPool(viewPool);

        // recyclerView.addItemDecoration(itemDecoration);

        viewPool.setMaxRecycledViews(0, 20);

        layoutManager.setRecycleOffset(300);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        recyclerView.setAdapter(delegateAdapter);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        if (BANNER_LAYOUT) {
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 1) {

                @Override
                public void onViewRecycled(MainViewHolder holder) {
                    if (holder.itemView instanceof ViewPager) {
                        ((ViewPager) holder.itemView).setAdapter(null);
                    }
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    if (viewType == 1)
                        return new MainViewHolder(
                                LayoutInflater.from(ThreeActivity.this).inflate(R.layout.view_pager, parent, false));

                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {

                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (holder.itemView instanceof ViewPager) {
                        ViewPager viewPager = (ViewPager) holder.itemView;
                        viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
                        // from position to get adapter
                        viewPager.setAdapter(new PagerAdapter(this, viewPool));
                    }
                }
            });
        }
        if (GRID_LAYOUT) {
            GridLayoutHelper layoutHelper;
            layoutHelper = new GridLayoutHelper(4);
            layoutHelper.setMargin(0, 10, 0, 10);
            layoutHelper.setHGap(3);
            layoutHelper.setAspectRatio(4f);
            adapters.add(new SubAdapter(this, layoutHelper, 8));
        }

        if (GRID_LAYOUT) {
            GridLayoutHelper layoutHelper;
            layoutHelper = new GridLayoutHelper(2);
            layoutHelper.setMargin(0, 10, 0, 10);
            layoutHelper.setHGap(3);
            layoutHelper.setAspectRatio(3f);
            adapters.add(new SubAdapter(this, layoutHelper, 2));
        }

       /* if (FLOAT_LAYOUT) {
            FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
            layoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);
            layoutHelper.setDefaultLocation(100, 0);
            layoutHelper.setAdjuster(new FixAreaAdjuster(0, 100, 0, 0));
            VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            adapters.add(new SubAdapter(this, layoutHelper, 1, layoutParams));
        }*/

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff876384);
            helper.setMargin(10, 10, 10, 10);
            helper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, helper, 3) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
//                    LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//                    layoutParams.leftMargin = 10;
//                    layoutParams.topMargin = 10;
//                    layoutParams.rightMargin = 10;
//                    layoutParams.bottomMargin = 10;
//                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }
        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 4));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 5));
        }
        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 5));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            helper.setColWeights(new float[]{20f, 80f, 0f, 60f, 20f});
            helper.setAspectRatio(4);
            adapters.add(new SubAdapter(this, helper, 5));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 6));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 7));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            helper.setColWeights(new float[]{40f, 45f, 15f, 60f, 0f, 30f, 30f});
            adapters.add(new SubAdapter(this, helper, 7));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xffed7612);
//            helper.setMargin(10, 10, 10, 10);
//            helper.setPadding(10, 10, 10, 10);
            helper.setColWeights(new float[]{30f, 20f, 50f, 40f, 30f, 35f, 35f});
            adapters.add(new SubAdapter(this, helper, 7) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
//                    LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
//                    layoutParams.leftMargin = 10;
//                    layoutParams.topMargin = 10;
//                    layoutParams.rightMargin = 10;
//                    layoutParams.bottomMargin = 10;
//                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }

        if (STICKY_LAYOUT) {
            StickyLayoutHelper layoutHelper = new StickyLayoutHelper();
            //layoutHelper.setAspectRatio(4);
            SubAdapter subAdapter = new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(200)));
            VirtualLayoutManager.LayoutParams params = subAdapter.mLayoutParams;
            height = params.height;
            Log.d("ThreeActivity", "height:" + height);


            adapters.add(subAdapter);
        }
        if (SCROLL_FIX_LAYOUT) {
            ScrollFixLayoutHelper layoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT, 20, 20);
            layoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);
            adapters.add(new SubAdapter(this, layoutHelper, 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(100, 100);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }
        if (LINEAR_LAYOUT) {
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 20));
        }
        if (STICKY_LAYOUT) {
            StickyLayoutHelper layoutHelper = new StickyLayoutHelper();
            //layoutHelper.setAspectRatio(4);
            layoutHelper.setOffset(height);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(100))));
        }
        if (LINEAR_LAYOUT) {
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 100));
        }
        delegateAdapter.setAdapters(adapters);

        final Handler mainHandler = new Handler(Looper.getMainLooper());

        trigger = new Runnable() {
            @Override
            public void run() {
                // recyclerView.scrollToPosition(22);
                // recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.requestLayout();
                // mainHandler.postDelayed(trigger, 1000);
            }
        };

        mainHandler.postDelayed(trigger, 1000);

        delegateAdapter.setAdapters(adapters);

    }
}
