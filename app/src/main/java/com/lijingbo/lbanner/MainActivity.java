package com.lijingbo.lbanner;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lijingbo.lbanner.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List< NewsBean > newsLists;
    private ViewPager vp_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        vp_image = (ViewPager) findViewById(R.id.vp_image);
    }

    private void initData() {
        newsLists = new ArrayList<>();
        newsLists.add(new NewsBean(R.drawable.a, "新西兰风景"));
        newsLists.add(new NewsBean(R.drawable.b, "大峡谷地质公园"));
        newsLists.add(new NewsBean(R.drawable.c, "阿西尼伯阴山风景"));
        newsLists.add(new NewsBean(R.drawable.d, "大新疆风光"));
        newsLists.add(new NewsBean(R.drawable.e, "美好的大草原，风景，摄影"));
    }

    private void initListener() {
        vp_image.setAdapter(new NewsImageAdapter());
    }

    class NewsImageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return newsLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
//            final int newPosition = position % newsLists.size();
            View View = new View(MainActivity.this);
            View.setBackgroundResource(newsLists.get(position).getImageResource());
            container.addView(View);
            //点击当前显示的view,进入点击操作。
            View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "点击了：" + newsLists.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            return View;
        }
    }


}
