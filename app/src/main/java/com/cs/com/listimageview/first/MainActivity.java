package com.cs.com.listimageview.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs.com.listimageview.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvTest1;
    private Button mBtnTest;
    private RecyclerView mRlTest1;
    private MyAdapter adapter;
    //private List<ImageBean.ResultsBean> mData =new ArrayList<>();
    private List<MultipleItem> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        mTvTest1 = (TextView) findViewById(R.id.tv_test1);
        mBtnTest = (Button) findViewById(R.id.btn_test1);

        mBtnTest.setOnClickListener(this);
        mRlTest1 = (RecyclerView) findViewById(R.id.rl_test1);
        mRlTest1.setOnClickListener(this);
        mRlTest1.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(mData);
        mRlTest1.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:

                String json = getFromAssets("json");
                Log.d("TTT", json);

                ImageBean result = new Gson().fromJson(json, ImageBean.class);

                List<ImageBean.ResultsBean> list = result.getResults();


                for (int i = 0; i < list.size(); i++) {
                    List<String> url = list.get(i).getUrl();
                    int size = url.size();
                    if (size == 1) {
                        MultipleItem item = new MultipleItem(MultipleItem.TYPE1, list.get(i));
                        mData.add(item);

                    } else if (size == 2) {
                        MultipleItem item = new MultipleItem(MultipleItem.TYPE2, list.get(i));
                        mData.add(item);
                    }else if (size == 3) {
                        MultipleItem item = new MultipleItem(MultipleItem.TYPE3, list.get(i));
                        mData.add(item);
                    }else if (size == 4) {
                        MultipleItem item = new MultipleItem(MultipleItem.TYPE4, list.get(i));
                        mData.add(item);
                    }else if (size == 5) {
                        MultipleItem item = new MultipleItem(MultipleItem.TYPE5, list.get(i));
                        mData.add(item);
                    }else {
                        MultipleItem item = new MultipleItem(MultipleItem.TYPE5, list.get(i));
                        mData.add(item);
                    }
                }

                adapter.notifyDataSetChanged();


/*                OkGo.<String>get("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                                String body = response.body();
                                Log.d("TTT", body);
                                ImageBean json = new Gson().fromJson(response.body(), ImageBean.class);

                                List<ImageBean.ResultsBean> list = json.getResults();


                                for (int i = 0; i < list.size(); i++) {
                                    List<String> url = list.get(i).getUrl();
                                    int size = url.size();
                                    if (size == 1) {
                                        MultipleItem item = new MultipleItem(MultipleItem.TYPE1, list.get(i));
                                        mData.add(item);

                                    } else if (size == 2) {
                                        MultipleItem item = new MultipleItem(MultipleItem.TYPE2, list.get(i));
                                        mData.add(item);
                                    }
                                }

                                adapter.notifyDataSetChanged();

                            }
                        })*/;


                break;
        }
    }
    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
