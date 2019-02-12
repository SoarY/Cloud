package com.soar.cloud.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.soar.cloud.adapter.FriendsAdapter;
import com.soar.cloud.adapter.HeaderFooterAdapter;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.bean.HotMovieBean;
import com.soar.cloud.retrofit.APIException;
import com.soar.cloud.retrofit.APIMain;
import com.soar.cloud.retrofit.ExceptionEngine;
import com.soar.cloud.retrofit.HttpResultFunc;
import com.soar.cloud.retrofit.MyObserver;
import com.soar.cloud.retrofit.RetrofitClient;
import com.soar.cloud.view.LoadingView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class FriendsViewModel extends BaseViewModel {

    public FriendsAdapter adapter;
    public HeaderFooterAdapter headerAdapter = new HeaderFooterAdapter(adapter = new FriendsAdapter());

    public FriendsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getHotMovie() {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<HotMovieBean> observable = instance.getApi(APIMain.API_DOUBAN).getHotMovie()
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(getLifecycleProvider(), observable, new MyObserver<HotMovieBean>() {

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
            }

            @Override
            public void onNext(HotMovieBean bean) {
                adapter.setData(bean.subjects);
                viewState(0, LoadingView.State.done);
            }

            @Override
            protected void onError(APIException ex) {
                if (ex.getCode() == ExceptionEngine.ERROR.ERROR_NET)
                    uiLiveData.toastEvent.show(ex.getDisplayMessage());
                viewState(1, ex.getCode() == ExceptionEngine.ERROR.ERROR_NET ? LoadingView.State.error : LoadingView.State.error);
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
