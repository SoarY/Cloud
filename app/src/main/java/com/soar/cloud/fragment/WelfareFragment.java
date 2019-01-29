package com.soar.cloud.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseLazyFragment;
import com.soar.cloud.databinding.FragmentWelfareBinding;
import com.soar.cloud.utils.CommonUtils;
import com.soar.cloud.utils.ToastUtils;
import com.soar.cloud.view.GridSpacingItemDecoration;
import com.soar.cloud.view.LoadingView;
import com.soar.cloud.vm.WelfareViewModel;


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
        binding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, CommonUtils.dip2px(4)));

        viewModel.adapter.setItemClickListener(position -> ToastUtils.showToast(position + ""));
    }

    @Override
    protected void lazyData() {
        viewModel.viewState(1, LoadingView.State.ing);
        viewModel.getGankIoData(true);
    }

}
