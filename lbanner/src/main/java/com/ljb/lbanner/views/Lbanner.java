package com.ljb.lbanner.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljb.lbanner.R;
import com.ljb.lbanner.bean.NewsBean;
import com.ljb.lbanner.utils.BitmapUtils;
import com.ljb.lbanner.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: com.lijingbo.lbanner.view.Lbanner.java
 * @Author: Li Jingbo
 * @Date: 2016-05-11 11:00
 * @Version V1.0 <描述当前版本功能>
 */
public class Lbanner extends RelativeLayout {
    private static final String TAG = "Lbanner";
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.ljb.lbanner";
    private static final int CODE_LOOP = 0;

    private ViewPager vp_image;
    private TextView tv_intro;
    private LinearLayout ll_donot_group;

    private List< NewsBean > newsLists;

    private boolean mLoop;
    private int mLoopovertime;

    //通过handler实现自动播放
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //设置viewpager的页面为当前页面+1
            switch ( msg.what ) {
                case CODE_LOOP:
                    vp_image.setCurrentItem(vp_image.getCurrentItem() + 1);
                    handler.sendEmptyMessageDelayed(CODE_LOOP, mLoopovertime);
                    break;
            }

        }
    };

    public Lbanner(Context context) {
        super(context);
        initView();
    }

    public Lbanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Lbanner);
        mLoop = typedArray.getBoolean(R.styleable.Lbanner_loop, false);
        mLoopovertime = typedArray.getInteger(R.styleable.Lbanner_loopovertime, 5000);
        initView();
    }


    private void initView() {
        View.inflate(getContext(), R.layout.view_lbanner, this);
        vp_image = (ViewPager) findViewById(R.id.vp_image);
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        ll_donot_group = (LinearLayout) findViewById(R.id.ll_donot_group);
    }

    public void setResoutseLists(List< NewsBean > lists) {
        if ( newsLists == null ) {
            newsLists = new ArrayList<>();
        } else {
            newsLists.clear();
        }
        newsLists.addAll(lists);
        initPoint(newsLists.size());
        initListener();
    }

    private void initListener() {
        vp_image.setAdapter(new NewsImageAdapter());
        vp_image.addOnPageChangeListener(new VpOnPageChangeListener());
        int centerValue = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % newsLists.size();
        //设置viewpager开始的位置，实现左右无限滑动
        vp_image.setCurrentItem(centerValue);

        //只有当loop为true并且循环时间大于1秒
        if ( mLoop && mLoopovertime >= 1000 ) {
            handler.sendEmptyMessageDelayed(CODE_LOOP, mLoopovertime);
        }
    }

    //初始化point
    private void initPoint(int number) {
        for ( int i = 0; i < number; i++ ) {
            View view = new View(getContext());
            view.setBackgroundResource(R.drawable.point_unfocus);
            int dipValue = DensityUtil.dip2px(getContext(), 8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dipValue, dipValue);
            if ( i > 0 ) {
                params.leftMargin = DensityUtil.dip2px(getContext(), 6);
            }
            view.setLayoutParams(params);
            ll_donot_group.addView(view);
        }
    }

    public void setLoop(Boolean loop) {
        mLoop = loop;
    }

    public void setLoopOverTime(int loopOverTime) {
        mLoopovertime = loopOverTime;
    }


    class NewsImageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            //Viewpager的数量设置为int的最大值，实现无限循环
            return Integer.MAX_VALUE;
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
        public Object instantiateItem(ViewGroup container, int position) {
            //对新的位置进行去余操作，可以得到真实的位置，然后从lists中获取数据。
            final int newPosition = position % newsLists.size();
            View view = new View(getContext());
            view.setBackgroundDrawable(BitmapUtils.relaseDrawable(getContext(), newsLists.get(newPosition).getImageResource()));
            container.addView(view);
            //点击当前显示的view,进入点击操作。
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(newPosition);
                }
            });
            return view;
        }
    }

    class VpOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(final int position) {
            //根据不同的页面，显示不同的文本和设置point的颜色
            updateIntroAndPoint(position % newsLists.size());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void updateIntroAndPoint(int position) {
        tv_intro.setText(newsLists.get(position).getName());
        for ( int i = 0; i < ll_donot_group.getChildCount(); i++ ) {
            //当前选中的point设置为白色
            if ( position == i ) {
                ll_donot_group.getChildAt(i).setBackgroundResource(R.drawable.point_focus);
            } else {
                ll_donot_group.getChildAt(i).setBackgroundResource(R.drawable.point_unfocus);
            }
        }
    }

    public interface LbannerItemClickListener {
        void itemClick(int newPosition);
    }

    private LbannerItemClickListener listener;

    public void setOnLbannerItemClickListener(LbannerItemClickListener listener) {
        this.listener = listener;
    }

}
