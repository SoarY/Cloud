package com.soar.cloud.vm;

import android.app.Application;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.soar.cloud.adapter.ImageAdapter;
import com.soar.cloud.base.BaseViewModel;

/**
 * NAME：YONG_
 * Created at: 2019/1/11
 * Describe:
 */
public class ImageViewModel extends BaseViewModel {

    public ImageAdapter adapter = new ImageAdapter();

    public ObservableInt position = new ObservableInt();

    public ImageViewModel(@NonNull Application application) {
        super(application);
    }

    public ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int pos) {
            position.set(pos);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
