package com.ljb.lbanner.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.InputStream;

/**
 * @FileName: com.lijingbo.lbanner.utils.BitmapUtils.java
 * @Author: Li Jingbo
 * @Date: 2016-05-11 13:33
 * @Version V1.0 <描述当前版本功能>
 */
public class BitmapUtils {
    private static final String TAG = "BitmapUtils";

    public static BitmapDrawable relaseDrawable(Context ctx, int resource) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = ctx.getResources().openRawResource(resource);
        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
        return new BitmapDrawable(ctx.getResources(), bm);



    }
}
