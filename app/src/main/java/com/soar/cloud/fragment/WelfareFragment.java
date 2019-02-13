package com.soar.cloud.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.activity.ImageActivity;
import com.soar.cloud.base.BaseLazyFragment;
import com.soar.cloud.bean.GankIoDataBean;
import com.soar.cloud.constant.RouteConstants;
import com.soar.cloud.databinding.FragmentWelfareBinding;
import com.soar.cloud.utils.CommonUtils;
import com.soar.cloud.view.GridSpacingItemDecoration;
import com.soar.cloud.view.LoadingView;
import com.soar.cloud.vm.WelfareViewModel;

import java.util.ArrayList;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class WelfareFragment extends BaseLazyFragment<FragmentWelfareBinding, WelfareViewModel> {

    public static WelfareFragment newInstance() {
        return new WelfareFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_welfare;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    private void initView() {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        /**
         * 下拉刷新成功后显示会闪一下
         * CommonUtils.takeEven
         * 取偶设置上下左右边距是一样的话，系统就会复用，就消除了图片不能复用 闪跳的情况
         */
        binding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, CommonUtils.takeEven(CommonUtils.dip2px(4))));

        viewModel.adapter.setItemClickListener(position -> {
                    ArrayList<String> imageUrls = new ArrayList<>();
                    for (GankIoDataBean.ResultBean data : viewModel.datas)
                        imageUrls.add(data.url);
                    ARouter.getInstance()
                            .build(RouteConstants.Discover.WELFARE_IMG)
                            .withInt(ImageActivity.POSITION,position)
                            .withStringArrayList(ImageActivity.IMAGE_URLS, imageUrls)
                            .navigation();
                }
        );
    }

    @Override
    protected void lazyData() {
        viewModel.viewState(1, LoadingView.State.ing);
        viewModel.getGankIoData(true);
    }

}
