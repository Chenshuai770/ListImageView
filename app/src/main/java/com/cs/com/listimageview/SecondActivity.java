package com.cs.com.listimageview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lopei.collageview.CollageView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private CollageView mCollageView;
    private String url1="http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg";
    private String url2="https://ww1.sinaimg.cn/large/0065oQSqly1g2hekfwnd7j30sg0x4djy.jpg";
    private String url3="https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg";
    private String url4="https://ws1.sinaimg.cn/large/0065oQSqly1fytdr77urlj30sg10najf.jpg";
    private String url5="https://ws1.sinaimg.cn/large/0065oQSqly1fymj13tnjmj30r60zf79k.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        List<String> mList =new ArrayList<>();
        mList.add(url1);
        mList.add(url2);
        mList.add(url3);
        mList.add(url4);
        mList.add(url5);mList.add(url1);
        mList.add(url2);
        mList.add(url3);
        mList.add(url4);
        mList.add(url5);
        mCollageView = (CollageView) findViewById(R.id.collageView);


        mCollageView
                .photoMargin(1)
                .photoPadding(3)
                .backgroundColor(Color.RED)
                .photoFrameColor(Color.BLUE)
                .useFirstAsHeader(true) // makes first photo fit device widtdh and use full line
                .defaultPhotosForLine(9) // sets default photos number for line of photos (can be changed by program at runtime)
                //.iconSelector(this, getResources().getDimensionPixelSize(R.dimen.icon_size)) (or use 0 as size to wrap content)
                .useCards(true) // adds cardview backgrounds to all photos
                .maxWidth(100) // will resize images if their side is bigger than 100
                .placeHolder(R.drawable.ic_launcher_background) //adds placeholder resource
                .headerForm(CollageView.ImageForm.IMAGE_FORM_SQUARE) // sets form of image for header (if useFirstAsHeader == true)
                .photosForm(CollageView.ImageForm.IMAGE_FORM_HALF_HEIGHT) //sets form of image for other photos
                .loadPhotos(mList); // here you can use Array/List of photo urls or array of resource ids
    }
}
