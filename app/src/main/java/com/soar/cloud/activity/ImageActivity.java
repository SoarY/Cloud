package com.soar.cloud.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseActivity;
import com.soar.cloud.constant.RouteConstants;
import com.soar.cloud.databinding.ActivityImageBinding;
import com.soar.cloud.view.StatusBarCompat;
import com.soar.cloud.vm.ImageViewModel;

import java.util.ArrayList;

/**
 * NAME：YONG_
 * Created at: 2019/1/10
 * Describe:
 */
@Route(path = RouteConstants.Discover.WELFARE_IMG)
public class ImageActivity extends BaseActivity<ActivityImageBinding, ImageViewModel> {

    public static final String POSITION = "position";
    public static final String IMAGE_URLS = "imageUrls";

    @Autowired
    public int position;
    @Autowired
    public ArrayList<String> imageUrls;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_image;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        initView();
    }

    private void initView() {
        StatusBarCompat.setTransparentStatus(context, binding.viewStatusBar, Color.TRANSPARENT);

        viewModel.setContext(context);
        viewModel.adapter.setContext(context);
        viewModel.adapter.setData(imageUrls);
        binding.viewPager.setAdapter(viewModel.adapter);
        binding.viewPager.setCurrentItem(position);
        viewModel.position.set(position);
    }
}
