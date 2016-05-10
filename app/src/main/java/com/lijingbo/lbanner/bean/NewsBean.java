package com.lijingbo.lbanner.bean;

/**
 * @FileName: com.lijingbo.customview_viewpager2.bean.NewsBean.java
 * @Author: Li Jingbo
 * @Date: 2016-05-09 21:47
 * @Version V1.0 <描述当前版本功能>
 */
public class NewsBean {
    private static final String TAG = "NewsBean";

    private String name;
    private int imageResource;

    public NewsBean(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
