# LBanner
##功能
1. 通过ViewPager实现轮播大图
2. 延迟5秒自动无限循环
3. 实现图片点击事件

##知识点
- Handle实现自动循环
  - 使用了handle.sendEmptyMessageDelayed()方法，延迟一定时间执行
  - 在handleMessage()方法中，调用Viewpager的setCurrentItem()方法，可以直接设置显示的页面。
  - 先通过ViewPager的getCurrentItem()方法获取到当前的位置，然后+1，然后通过setCurrentItem()方法，实现到下一个页面；上面步骤完成以后，再次调用handle.sendEmptyMessageDelayed()方法，实现自动无限循环功能

- 创建point：可以使用图片，也可以通过shape创建。

        <?xml version="1.0" encoding="utf-8"?>
		<shape xmlns:android="http://schemas.android.com/apk/res/android"
		       android:shape="oval">
		    <solid android:color="@android:color/white"></solid>
		</shape>

- 创建多个point，并加入到LinearLayout中，每个point大小为8dp,point间隔为6dp.
> 注意dp和sp的转换。
		
		for ( int i = 0; i < newsLists.size(); i++ ) {
		            View view = new View(this);
		            view.setBackgroundResource(R.drawable.point_unfocus);
		            int dipValue = DensityUtil.dip2px(MainActivity.this, 8);
		            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dipValue, dipValue);
		            if ( i > 0 ) {
		                params.leftMargin = DensityUtil.dip2px(MainActivity.this, 6);
		            }
		            view.setLayoutParams(params);
		            ll_donot_group.addView(view);
		        }

其他ViewPager注意事项请参考：[Customview viewpager](http://www.lijingbo.cc/customview_viewpager/)