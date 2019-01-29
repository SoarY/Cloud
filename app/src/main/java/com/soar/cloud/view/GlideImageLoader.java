package com.soar.cloud.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soar.cloud.R;
import com.youth.banner.loader.ImageLoader;

/**
 * NAME：YONG_
 * Created at: 2019/1/9
 * Describe:
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path)
                .placeholder(R.mipmap.ic_item_one)
                .error(R.mipmap.ic_item_one)
                .into(imageView);
    }
}
