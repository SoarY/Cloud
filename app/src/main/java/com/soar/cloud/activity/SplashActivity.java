package com.soar.cloud.activity;

import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseActivity;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.constant.RouteConstants;
import com.soar.cloud.databinding.ActivitySplashBinding;


public class SplashActivity extends BaseActivity<ActivitySplashBinding, BaseViewModel> {

    private final static long DELAY_MILLIS = 3000;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
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
        new Handler().postDelayed(() -> {
            ARouter.getInstance()
                    .build(RouteConstants.Rule.MAIN_ACTIVITY)
                    .withTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out)
                    .navigation(this);
            finish();
        }, DELAY_MILLIS);
    }

}
