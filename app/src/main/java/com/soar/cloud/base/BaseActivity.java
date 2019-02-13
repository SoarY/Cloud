package com.soar.cloud.base;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.cloud.event.FinishLiveEvent;
import com.soar.cloud.utils.ToastUtils;
import com.trello.navi2.component.support.NaviAppCompatActivity;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * NAME：YONG_
 * Created at: 2018/12/7 11
 * Describe:
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends NaviAppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    public VM viewModel;
    public V binding;

    public Activity context;

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        context=this;

        //Databinding
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        binding.setVariable(initVariableId(), viewModel = getViewModel());
        viewModel.injectLifecycleProvider(NaviLifecycle.createActivityLifecycleProvider(this));

        //注册常用liveData观察者
        registorUILiveData();
    }

    private VM getViewModel() {
        Type type = getClass().getGenericSuperclass();
        Class modelClass;
        if (type instanceof ParameterizedType)
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        else
            modelClass = BaseViewModel.class;//如果没有指定泛型参数，则默认使用BaseViewModel
        return (VM) ViewModelProviders.of(this).get(modelClass);
    }

    private void registorUILiveData() {
        viewModel.uiLiveData.activityEvent.observe(this, s -> ARouter.getInstance().build(s).navigation());

        viewModel.uiLiveData.toastEvent.observe(this, s -> ToastUtils.showToast(s));

        viewModel.uiLiveData.finishEvent.observe(this, result -> {
            if (result != null && result.state == FinishLiveEvent.FINISH_RESULT)
                setResult(result.resultCode, result.intent);
            finish();
        });

        viewModel.uiLiveData.dialogEvent.observe(this, dialogInfo -> {
            //            if (dialogInfo.isShow)
            //                DialogView.showDialog(this, dialogInfo.message);
            //            else
            //                DialogView.hideDialog();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
        viewModel = null;
        binding.unbind();
    }
}
