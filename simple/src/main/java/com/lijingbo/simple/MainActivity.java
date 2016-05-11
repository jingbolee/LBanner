package com.lijingbo.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ljb.lbanner.bean.NewsBean;
import com.ljb.lbanner.views.Lbanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private Lbanner lbanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        lbanner = (Lbanner) findViewById(R.id.lb_simple);

        Lbanner lbanner=new Lbanner(this);
        setContentView(lbanner);
        List< NewsBean > newsLists = new ArrayList<>();
        newsLists.add(new NewsBean(R.drawable.a, "新西兰风景"));
        newsLists.add(new NewsBean(R.drawable.b, "大峡谷地质公园"));
        newsLists.add(new NewsBean(R.drawable.c, "阿西尼伯阴山风景"));
        newsLists.add(new NewsBean(R.drawable.d, "大新疆风光"));
        newsLists.add(new NewsBean(R.drawable.e, "美好的大草原，风景，摄影"));
        lbanner.setLoop(true);
        lbanner.setLoopOverTime(900);
        lbanner.setResoutseLists(newsLists);


        lbanner.setOnLbannerItemClickListener(new Lbanner.LbannerItemClickListener() {
            @Override
            public void itemClick(int newPosition) {
                Toast.makeText(MainActivity.this, "点击了第" + newPosition + "个", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
