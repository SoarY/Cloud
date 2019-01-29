package com.soar.cloud.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseLazyFragment;
import com.soar.cloud.databinding.FooterItemEverydayBinding;
import com.soar.cloud.databinding.FragmentEverydayBinding;
import com.soar.cloud.databinding.HeaderItemEverydayBinding;
import com.soar.cloud.utils.ToastUtils;
import com.soar.cloud.view.GlideImageLoader;
import com.soar.cloud.vm.EverydayViewModel;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class EverydayFragment extends BaseLazyFragment<FragmentEverydayBinding, EverydayViewModel> {

    private static final int DELAY_TIME = 3000;

    private HeaderItemEverydayBinding mHeaderBinding;

    public static EverydayFragment newInstance() {
        return new EverydayFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_everyday;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    public void onStart() {
        super.onStart();
        mHeaderBinding.banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mHeaderBinding.banner.stopAutoPlay();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    private void initView() {
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_item_everyday, null, false);
        mHeaderBinding.setVariable(BR.vm, viewModel);
        mHeaderBinding.banner.setImageLoader(new GlideImageLoader()).setDelayTime(DELAY_TIME);
        mHeaderBinding.banner.setOnBannerListener(position -> ToastUtils.showToast(position+""));
        mHeaderBinding.includeEveryday.ibDried.setOnClickListener(v -> ToastUtils.showToast("干货"));
        mHeaderBinding.includeEveryday.ibDailyDay.setOnClickListener(v -> ToastUtils.showToast("推荐"));
        mHeaderBinding.includeEveryday.ibAndroidPlay.setOnClickListener(v -> ToastUtils.showToast("玩安卓"));
        mHeaderBinding.includeEveryday.ibMovieHot.setOnClickListener(v -> ToastUtils.showToast("热映"));

        FooterItemEverydayBinding mFooterBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.footer_item_everyday, null, false);

        viewModel.headerAdapter.addHeaderView(mHeaderBinding.getRoot());
        viewModel.headerAdapter.addFooterView(mFooterBinding.getRoot());

        viewModel.adapter.setItemClickListener(position -> ToastUtils.showToast(position+""));
    }

    @Override
    protected void lazyData() {
        viewModel.getBannerData();
        viewModel.getGankIoDay();
    }

}
