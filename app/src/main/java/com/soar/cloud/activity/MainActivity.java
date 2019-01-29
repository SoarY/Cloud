package com.soar.cloud.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.soar.cloud.R;
import com.soar.cloud.adapter.BottomPagerAdapter;
import com.soar.cloud.base.BaseActivity;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.constant.RouteConstants;
import com.soar.cloud.databinding.ActivityMainBinding;
import com.soar.cloud.databinding.LayoutNavigationHeaderBinding;
import com.soar.cloud.fragment.DiscoverFragment;
import com.soar.cloud.fragment.FriendsFragment;
import com.soar.cloud.fragment.MusicFragment;
import com.soar.cloud.utils.ToastUtils;

@Route(path = RouteConstants.Rule.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        binding.layToolbarMain.navigationTool.setTextVisibility(false);
        binding.layToolbarMain.navigationTool.enableAnimation(false);
        binding.layToolbarMain.navigationTool.enableShiftingMode(false);
        binding.layToolbarMain.navigationTool.enableItemShiftingMode(false);
        for (int i = 0; i < binding.layToolbarMain.navigationTool.getItemCount(); i++)
            binding.layToolbarMain.navigationTool.setIconTintList(i, getResources().getColorStateList(R.color.select_tool_main));
        binding.layToolbarMain.flTitleMenu.setOnClickListener(v -> binding.drawerLayout.openDrawer(GravityCompat.START));
        binding.layToolbarMain.ivTitleSearch.setOnClickListener(v -> ToastUtils.showToast("Search"));

        BottomPagerAdapter adapter = new BottomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MusicFragment.newInstance());
        adapter.addFragment(DiscoverFragment.newInstance());
        adapter.addFragment(FriendsFragment.newInstance());
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.setAdapter(adapter);
        binding.layToolbarMain.navigationTool.setupWithViewPager(binding.viewPager);

        View headerView = binding.navigationView.getHeaderView(0);
        LayoutNavigationHeaderBinding headBind = DataBindingUtil.bind(headerView);

        headBind.llNavHomepage.setOnClickListener(listener);
        headBind.llNavScanDownload.setOnClickListener(listener);
        headBind.llNavDeedback.setOnClickListener(listener);
        headBind.llNavAbout.setOnClickListener(listener);
        headBind.llNavLogin.setOnClickListener(listener);
        headBind.llNavCollect.setOnClickListener(listener);
        headBind.llNavExit.setOnClickListener(listener);
    }

    private View.OnClickListener listener = v -> {
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        switch (v.getId()) {
            case R.id.ll_nav_homepage:// 主页
                ToastUtils.showToast("主页");
                break;
            case R.id.ll_nav_scan_download://扫码下载
                ToastUtils.showToast("扫码下载");
                break;
            case R.id.ll_nav_deedback:// 问题反馈
                ToastUtils.showToast("问题反馈");
                break;
            case R.id.ll_nav_about:// 关于云阅
                ToastUtils.showToast("关于云阅");
                break;
            case R.id.ll_nav_collect:// 玩安卓收藏
                ToastUtils.showToast("玩安卓收藏");
                break;
            case R.id.ll_nav_login:// 玩安卓登录
                ToastUtils.showToast("玩安卓登录");
                break;
            case R.id.ll_nav_exit:// 退出应用
                ToastUtils.showToast("退出应用");
                break;
        }
    };
}
