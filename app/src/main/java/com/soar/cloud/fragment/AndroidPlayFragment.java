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
import com.soar.cloud.databinding.FragmentAndroidPlayBinding;
import com.soar.cloud.databinding.HeaderAndroidPalyBinding;
import com.soar.cloud.utils.ToastUtils;
import com.soar.cloud.view.GlideImageLoader;
import com.soar.cloud.view.LoadingView;
import com.soar.cloud.vm.AndroidPlayViewModel;
import com.youth.banner.BannerConfig;

/**
 * NAME：YONG_
 * Created at: 2019/1/9
 * Describe:
 */
public class AndroidPlayFragment extends BaseLazyFragment<FragmentAndroidPlayBinding, AndroidPlayViewModel> {

    private static final int DELAY_TIME = 3000;

    private HeaderAndroidPalyBinding mHeaderBinding;

    public static AndroidPlayFragment newInstance() {
        return new AndroidPlayFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_android_play;
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
        binding.materialHeaderView.setColorSchemeResources(R.color.colorAccent);

        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_android_paly, null, false);
        mHeaderBinding.setVariable(BR.vm, viewModel);
        mHeaderBinding.banner.setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setDelayTime(DELAY_TIME);
        mHeaderBinding.banner.setOnBannerListener(position -> ToastUtils.showToast(position+""));
        mHeaderBinding.ivBannerOne.setOnClickListener(v -> ToastUtils.showToast("BannerOne"));
        mHeaderBinding.ivBannerTwo.setOnClickListener(v -> ToastUtils.showToast("BannerTwo"));

        viewModel.headerAdapter.addHeaderView(mHeaderBinding.getRoot());
        viewModel.adapter.setItemClickListener(position -> ToastUtils.showToast(position+""));
    }

    @Override
    protected void lazyData() {
        viewModel.viewState(1, LoadingView.State.ing);
        viewModel.getBannerData();
        viewModel.getHomeList(true);
    }

}
