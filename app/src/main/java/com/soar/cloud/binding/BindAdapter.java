package com.soar.cloud.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.soar.cloud.view.LoadingView;
import com.youth.banner.Banner;

import java.util.List;


/**
 * NAME：YONG_
 * Created at: 2018/12/14 17
 * Describe:
 */
public class BindAdapter {

    @BindingAdapter("onPageChangeListener")
    public static void addOnPageChangeListener(ViewPager view, ViewPager.OnPageChangeListener listener) {
        view.addOnPageChangeListener(listener);
    }

    @BindingAdapter("onRefreshListener")
    public static void setOnRefreshListener(SmartRefreshLayout view, OnRefreshListener listener) {
        view.setOnRefreshListener(listener);
    }

    @BindingAdapter("onLoadMoreListener")
    public static void setOnLoadMoreListener(SmartRefreshLayout view, OnLoadMoreListener listener) {
        view.setOnLoadMoreListener(listener);
    }

    @BindingAdapter("stopRefresh")
    public static void setStopRefresh(SmartRefreshLayout view, boolean stopRefresh) {
        view.finishRefresh();
        view.finishLoadMore();
    }

    @BindingAdapter("viewSwitcher")
    public static void setViewSwitcher(ViewSwitcher view, int whichChild) {
        view.setDisplayedChild(whichChild);
    }

    @BindingAdapter("loadState")
    public static void setLoadState(LoadingView view, LoadingView.State state) {
        view.notifyDataChanged(state);
    }

    @BindingAdapter(value = {"bannerData", "bannerTitle"}, requireAll = false)
    public static void setBannerData(Banner view, List<Object> datas, List<String> titles) {
        if (titles != null)
            view.setBannerTitles(titles);
        if (datas != null && datas.size() > 0)
            view.setImages(datas).start();
    }

    @BindingAdapter(value = {"imgSource", "placeholder", "errorholder"}, requireAll = false)
    public static void setImgSource(ImageView view, String imgSource, Drawable placeholder, Drawable errorholder) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)
                .error(errorholder);
        Glide.with(view.getContext())
                .load(imgSource)
                .apply(options)
                .into(view);
    }
}
