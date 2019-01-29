package com.soar.cloud.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.soar.cloud.event.ActivityLiveData;
import com.soar.cloud.event.DialogLiveData;
import com.soar.cloud.event.FinishLiveEvent;
import com.soar.cloud.event.ToastLiveData;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * NAME：YONG_
 * Created at: 2018/12/7 11
 * Describe:
 */
public class BaseViewModel extends AndroidViewModel {

    private LifecycleProvider<ActivityEvent> lifecycle;

    public UIChangeLiveData uiLiveData = new UIChangeLiveData();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 注入RxLifecycle生命周期
     */
    public void injectLifecycleProvider(LifecycleProvider<ActivityEvent> lifecycle) {
        this.lifecycle = lifecycle;
    }

    public LifecycleProvider<ActivityEvent> getLifecycleProvider() {
        return lifecycle;
    }

    public class UIChangeLiveData {
        public ActivityLiveData activityEvent = new ActivityLiveData();
        public FinishLiveEvent finishEvent = new FinishLiveEvent();
        public ToastLiveData toastEvent = new ToastLiveData();
        public DialogLiveData dialogEvent = new DialogLiveData();
    }

    /**
     * 若有引用到Activity,比如Context等
     *
     * 在Activity销毁时,ViewModel重写此方法释放引用
     */
    public void onDestroy() {
    }
}
