package com.soar.cloud.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soar.cloud.R;
import com.soar.cloud.adapter.MePagerAdapter;
import com.soar.cloud.base.BaseLazyFragment;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.databinding.FragmentDiscoverBinding;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class DiscoverFragment extends BaseLazyFragment<FragmentDiscoverBinding, BaseViewModel> {

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_discover;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    private void initView() {
        MePagerAdapter pagerAdapter = new MePagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(getString(R.string.everyday), EverydayFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.welfare), WelfareFragment.newInstance());
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void lazyData() {
    }
}
