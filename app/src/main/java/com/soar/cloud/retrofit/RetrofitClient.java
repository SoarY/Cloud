package com.soar.cloud.retrofit;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * YONG_
 */
public class RetrofitClient {

    private static volatile RetrofitClient mInstance;
    private HashMap<String, API> apis = new HashMap<>();

    private RetrofitClient() {
        getApi();
    }

    public static RetrofitClient getInstance() {
        if (mInstance == null)
            synchronized (RetrofitClient.class) {
                if (mInstance == null)
                    mInstance = new RetrofitClient();
            }
        return mInstance;
    }

    public API getApi() {
        return getApi(APIMain.API_GANKIO);
    }

    public API getApi(String urlMain) {
        if (!apis.containsKey(urlMain)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(urlMain)
                    .build();
            API api = retrofit.create(API.class);
            apis.put(urlMain, api);
        }
        return apis.get(urlMain);
    }

    /**
     * 订阅
     */
    public <T> void toSubscribe(LifecycleProvider<ActivityEvent> lifecycle, Observable<T> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(s);
    }
}
