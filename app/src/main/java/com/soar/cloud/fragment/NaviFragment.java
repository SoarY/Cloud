package com.soar.cloud.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseLazyFragment;
import com.soar.cloud.databinding.FragmentNaviBinding;
import com.soar.cloud.view.LoadingView;
import com.soar.cloud.vm.NaviViewModel;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class NaviFragment extends BaseLazyFragment<FragmentNaviBinding, NaviViewModel> {

    public static NaviFragment newInstance() {
        return new NaviFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_navi;
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
        viewModel.adapter.setOnSelectListener(position -> ((LinearLayoutManager) binding.xrvNaviDetail.getLayoutManager()).scrollToPositionWithOffset(position,0));
        binding.loadingView.setOnRetryListener(() ->lazyData() );
    }

    @Override
    protected void lazyData() {
        viewModel.viewState(1, LoadingView.State.ing);
        viewModel.getNaviJson();
    }
}
