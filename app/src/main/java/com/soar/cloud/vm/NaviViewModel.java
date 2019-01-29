package com.soar.cloud.vm;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.soar.cloud.adapter.NaviAdapter;
import com.soar.cloud.adapter.NaviContentAdapter;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.bean.NaviBean;
import com.soar.cloud.retrofit.APIException;
import com.soar.cloud.retrofit.APIMain;
import com.soar.cloud.retrofit.HttpResultFunc;
import com.soar.cloud.retrofit.MyObserver;
import com.soar.cloud.retrofit.RetrofitClient;
import com.soar.cloud.retrofit.ServerResultFunc;
import com.soar.cloud.view.LoadingView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * NAME：YONG_
 * Created at: 2019/1/11
 * Describe:
 */
public class NaviViewModel extends BaseViewModel {

    public ObservableInt whichChild = new ObservableInt();
    public ObservableField<LoadingView.State> loadState = new ObservableField<>(LoadingView.State.done);

    public NaviAdapter adapter = new NaviAdapter();
    public NaviContentAdapter naviContentAdapter = new NaviContentAdapter();

    public NaviViewModel(@NonNull Application application) {
        super(application);
    }

    public void viewState(int i, LoadingView.State state) {
        whichChild.set(i);
        loadState.set(state);
    }

    public void getNaviJson() {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<List<NaviBean>> observable = instance.getApi(APIMain.API_PLAY_ANDROID).getNaviJson()
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(getLifecycleProvider(), observable, new MyObserver<List<NaviBean>>() {

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
            }

            @Override
            public void onNext(List<NaviBean> datas) {
                adapter.setData(datas);
                naviContentAdapter.setData(datas);
                viewState(0, LoadingView.State.done);
            }

            @Override
            protected void onError(APIException ex) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
