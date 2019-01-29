package com.soar.cloud.utils;

import com.soar.cloud.MyApplication;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class CommonUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
