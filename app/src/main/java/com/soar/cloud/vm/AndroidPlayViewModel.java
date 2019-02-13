package com.soar.cloud.vm;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.soar.cloud.MyApplication;
import com.soar.cloud.R;
import com.soar.cloud.adapter.AndroidPlayAdapter;
import com.soar.cloud.adapter.HeaderFooterAdapter;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.bean.AndroidPlayBannerBean;
import com.soar.cloud.bean.ArticlesBean;
import com.soar.cloud.bean.DataBean;
import com.soar.cloud.constant.Constant;
import com.soar.cloud.retrofit.APIException;
import com.soar.cloud.retrofit.APIMain;
import com.soar.cloud.retrofit.ExceptionEngine;
import com.soar.cloud.retrofit.HttpResultFunc;
import com.soar.cloud.retrofit.MyObserver;
import com.soar.cloud.retrofit.RetrofitClient;
import com.soar.cloud.retrofit.ServerResultFunc;
import com.soar.cloud.view.LoadingView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class AndroidPlayViewModel extends BaseViewModel {

    private int pageIndex = Constant.page;

    public AndroidPlayAdapter adapter;
    public HeaderFooterAdapter headerAdapter = new HeaderFooterAdapter(adapter = new AndroidPlayAdapter());

    public ObservableField<List<Object>> bannerUrlDatas = new ObservableField<>();
    public ObservableField<List<String>> bannerTitleDatas = new ObservableField<>();
    public List<ArticlesBean> datas = new ArrayList<>();

    public ObservableBoolean stopRefresh = new ObservableBoolean();

    private Integer cid;
    private int totalCount;

    public AndroidPlayViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCID(Integer cid) {
        this.cid = cid;
    }

    public void getBannerData() {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<List<AndroidPlayBannerBean>> observable = instance.getApi(APIMain.API_PLAY_ANDROID).getAndroidPlayBanner()
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(getLifecycleProvider(), observable, new MyObserver<List<AndroidPlayBannerBean>>() {

            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
            }

            @Override
            public void onNext(List<AndroidPlayBannerBean> data) {
                List<String> titles = new ArrayList<>();
                List<Object> urls = new ArrayList<>();
                for (AndroidPlayBannerBean datum : data) {
                    titles.add(datum.title);
                    urls.add(datum.imagePath);
                }
                bannerTitleDatas.set(titles);
                bannerUrlDatas.set(urls);
            }

            @Override
            protected void onError(APIException ex) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public void getHomeList(boolean isRefreshOrLoad) {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<DataBean> observable = instance.getApi(APIMain.API_PLAY_ANDROID).getHomeList(pageIndex, cid)
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(getLifecycleProvider(), observable, new MyObserver<DataBean>() {

            @Override
            public void onNext(DataBean data) {
                if (pretreatment(data, isRefreshOrLoad)) return;
                //数据操作
                totalCount = data.total;
                datas.addAll(data.datas);
                adapter.setData(datas);
            }

            @Override
            protected void onError(APIException ex) {
                if (ex.getCode() == ExceptionEngine.ERROR.ERROR_NET)
                    uiLiveData.toastEvent.show(ex.getDisplayMessage());
                viewState(1, ex.getCode() == ExceptionEngine.ERROR.ERROR_NET ? LoadingView.State.error : LoadingView.State.error);
            }
        });
    }

    /**
     * 界面预处理
     */
    private boolean pretreatment(DataBean data, boolean isRefreshOrLoad) {
        stopRefresh.set(!stopRefresh.get());
        viewState(0, LoadingView.State.done);
        if (isRefreshOrLoad) {
            datas.clear();
            adapter.setData(datas);
        }
        if (data == null || data.datas == null || data.datas.size() == 0)
            return true;
        return false;
    }

    /**
     * 下拉刷新
     */
    public OnRefreshListener onRefresh = refreshLayout -> {
        pageIndex = Constant.page;
        getHomeList(true);
        getBannerData();
    };

    /**
     * 上拉加载
     */
    public OnLoadMoreListener onLoadMore = refreshLayout -> {
        if (datas.size() >= totalCount) {
            uiLiveData.toastEvent.show(MyApplication.getContext().getResources().getString(R.string.to_loaded));
            stopRefresh.set(!stopRefresh.get());
            return;
        }
        pageIndex++;
        getHomeList(false);
    };
}
