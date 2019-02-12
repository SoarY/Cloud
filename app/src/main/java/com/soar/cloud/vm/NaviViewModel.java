package com.soar.cloud.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.soar.cloud.adapter.NaviAdapter;
import com.soar.cloud.adapter.NaviContentAdapter;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.bean.NaviBean;
import com.soar.cloud.retrofit.APIException;
import com.soar.cloud.retrofit.APIMain;
import com.soar.cloud.retrofit.ExceptionEngine;
import com.soar.cloud.retrofit.HttpResultFunc;
import com.soar.cloud.retrofit.MyObserver;
import com.soar.cloud.retrofit.RetrofitClient;
import com.soar.cloud.retrofit.ServerResultFunc;
import com.soar.cloud.view.LoadingView;

import java.util.List;

import io.reactivex.Observable;

/**
 * NAME：YONG_
 * Created at: 2019/1/11
 * Describe:
 */
public class NaviViewModel extends BaseViewModel {

    public NaviAdapter adapter = new NaviAdapter();
    public NaviContentAdapter naviContentAdapter = new NaviContentAdapter();

    public NaviViewModel(@NonNull Application application) {
        super(application);
    }

    public void getNaviJson() {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<List<NaviBean>> observable = instance.getApi(APIMain.API_PLAY_ANDROID).getNaviJson()
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(getLifecycleProvider(), observable, new MyObserver<List<NaviBean>>() {

            @Override
            public void onNext(List<NaviBean> datas) {
                adapter.setData(datas);
                naviContentAdapter.setData(datas);
                viewState(0, LoadingView.State.done);
            }

            @Override
            protected void onError(APIException ex) {
                if (ex.getCode() == ExceptionEngine.ERROR.ERROR_NET)
                    uiLiveData.toastEvent.show(ex.getDisplayMessage());
                viewState(1, ex.getCode() == ExceptionEngine.ERROR.ERROR_NET ? LoadingView.State.error : LoadingView.State.error);
            }
        });
    }
}
