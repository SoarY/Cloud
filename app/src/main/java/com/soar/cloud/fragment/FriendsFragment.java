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
import com.soar.cloud.databinding.FragmentFriendsBinding;
import com.soar.cloud.databinding.HeaderItemDouBinding;
import com.soar.cloud.utils.ToastUtils;
import com.soar.cloud.view.LoadingView;
import com.soar.cloud.vm.FriendsViewModel;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class FriendsFragment extends BaseLazyFragment<FragmentFriendsBinding, FriendsViewModel> {

    public static FriendsFragment newInstance() {
        return new FriendsFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_friends;
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
        HeaderItemDouBinding mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_item_dou, null, false);
        viewModel.headerAdapter.addHeaderView(mHeaderBinding.getRoot());
        mHeaderBinding.llMovieTop.setOnClickListener(v -> ToastUtils.showToast("TOP"));

        viewModel.adapter.setItemClickListener(position -> ToastUtils.showToast(position + ""));
    }

    @Override
    protected void lazyData() {
        viewModel.viewState(1, LoadingView.State.ing);
        viewModel.getHotMovie();
    }
}
