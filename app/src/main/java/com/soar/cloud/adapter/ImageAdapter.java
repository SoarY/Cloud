package com.soar.cloud.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.soar.cloud.R;
import com.soar.cloud.databinding.ItemPageImageBinding;
import com.soar.cloud.utils.ToastUtils;

import java.util.List;

/**
 * NAME：YONG_
 * Created at: 2019/2/13
 * Describe:
 */
public class ImageAdapter extends PagerAdapter {

    private List<String> imageUrls;
    private Activity context;

    public void setData(List<String> imageUrls) {
        this.imageUrls = imageUrls;
        notifyDataSetChanged();
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageUrls == null ? 0 : imageUrls.size();
    }

    public String getItem(int position) {
        return imageUrls == null ? null : imageUrls.get(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        String item = getItem(position);
        ItemPageImageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.item_page_image, container, true);
        Glide.with(container.getContext()).load(item).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                binding.loading.setVisibility(View.GONE);
                ToastUtils.showToast("资源加载异常");
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                binding.loading.setVisibility(View.GONE);
                return false;
            }
        }).into(binding.ivImage);
        binding.ivImage.setOnPhotoTapListener((view, x, y) -> context.finish());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
