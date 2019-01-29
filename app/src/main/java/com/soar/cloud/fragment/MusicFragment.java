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
import com.soar.cloud.databinding.FragmentMusicBinding;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class MusicFragment extends BaseLazyFragment<FragmentMusicBinding, BaseViewModel> {

    public static MusicFragment newInstance() {
        return new MusicFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_music;
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
        pagerAdapter.addFragment(getString(R.string.android), AndroidPlayFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.navi), NaviFragment.newInstance());
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void lazyData() {

    }
}
