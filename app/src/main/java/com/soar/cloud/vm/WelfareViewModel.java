package com.soar.cloud.vm;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.soar.cloud.MyApplication;
import com.soar.cloud.R;
import com.soar.cloud.adapter.WelfareAdapter;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.bean.GankIoDataBean;
import com.soar.cloud.constant.Constant;
import com.soar.cloud.retrofit.APIException;
import com.soar.cloud.retrofit.ExceptionEngine;
import com.soar.cloud.retrofit.HttpResultFunc;
import com.soar.cloud.retrofit.MyObserver;
import com.soar.cloud.retrofit.RetrofitClient;
import com.soar.cloud.retrofit.ServerException;
import com.soar.cloud.view.LoadingView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class WelfareViewModel extends BaseViewModel {

    private int pageIndex = Constant.pageIndex;
    private int pageSize = Constant.pageSize;

    public WelfareAdapter adapter = new WelfareAdapter();

    public List<GankIoDataBean.ResultBean> datas = new ArrayList<>();

    public ObservableBoolean stopRefresh = new ObservableBoolean();

    public WelfareViewModel(@NonNull Application application) {
        super(application);
    }

    public void getGankIoData(boolean isRefreshOrLoad) {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<GankIoDataBean> observable = instance.getApi().getGankIoData("福利", pageIndex, pageSize)
                .map(bean -> {
                    if (bean.isError())
                        throw new ServerException(ExceptionEngine.ERROR.DEFAULT_ERROR, "isError");
                    return bean;
                })
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(getLifecycleProvider(), observable, new MyObserver<GankIoDataBean>() {

            @Override
            public void onNext(GankIoDataBean bean) {
                if (pretreatment(bean, isRefreshOrLoad)) return;
                List<GankIoDataBean.ResultBean> results = bean.getResults();
                datas.addAll(results);
                adapter.setData(datas);
            }

            @Override
            protected void onError(APIException ex) {
                viewState(1, ex.getCode() == ExceptionEngine.ERROR.ERROR_NET ? LoadingView.State.error : LoadingView.State.error);
            }
        });
    }

    /**
     * 界面预处理
     */
    private boolean pretreatment(GankIoDataBean data, boolean isRefreshOrLoad) {
        stopRefresh.set(!stopRefresh.get());
        viewState(0, LoadingView.State.done);
        if (isRefreshOrLoad) {
            datas.clear();
            adapter.setData(datas);
        }
        if (data == null || data.getResults() == null || data.getResults().size() == 0) {
            uiLiveData.toastEvent.show(MyApplication.getContext().getResources().getString(R.string.to_loaded));
            return true;
        }
        return false;
    }

    /**
     * 上拉加载
     */
    public OnLoadMoreListener onLoadMore = refreshLayout -> {
        pageIndex++;
        getGankIoData(false);
    };
}
