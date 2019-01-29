package com.soar.cloud.vm;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Handler;
import android.text.TextUtils;

import com.soar.cloud.adapter.EverydayAdapter;
import com.soar.cloud.adapter.HeaderFooterAdapter;
import com.soar.cloud.base.BaseViewModel;
import com.soar.cloud.bean.AndroidBean;
import com.soar.cloud.bean.GankIoDayBean;
import com.soar.cloud.constant.ConstantsImageUrl;
import com.soar.cloud.retrofit.APIException;
import com.soar.cloud.retrofit.ExceptionEngine;
import com.soar.cloud.retrofit.HttpResultFunc;
import com.soar.cloud.retrofit.MyObserver;
import com.soar.cloud.retrofit.RetrofitClient;
import com.soar.cloud.retrofit.ServerException;
import com.soar.cloud.utils.SPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * NAME：YONG_
 * Created at: 2018/12/26
 * Describe:
 */
public class EverydayViewModel extends BaseViewModel {

    public ObservableField<List<Object>> bannerDatas = new ObservableField<>();

    public EverydayAdapter adapter;
    public HeaderFooterAdapter headerAdapter = new HeaderFooterAdapter(adapter = new EverydayAdapter());

    public EverydayViewModel(Application application) {
        super(application);
    }

    public void getBannerData() {
        String[] bannerUrls = ConstantsImageUrl.HOME_BANNER_URLS;
        new Handler().postDelayed(() -> bannerDatas.set(Arrays.asList(bannerUrls[0], bannerUrls[1], bannerUrls[2], bannerUrls[3])), 3000);
    }

    public void getGankIoDay() {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<List<List<AndroidBean>>> observable = instance.getApi().getGankIoDay("2016", "11", "24")
                .map(datas -> {
                    if (datas.isError())
                        throw new ServerException(ExceptionEngine.ERROR.DEFAULT_ERROR, "isError");
                    GankIoDayBean.ResultsBean dataResult = datas.getResults();
                    List<List<AndroidBean>> lists = new ArrayList<>();
                    analogData(lists, dataResult);
                    return lists;
                })
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(getLifecycleProvider(), observable, new MyObserver<List<List<AndroidBean>>>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(List<List<AndroidBean>> datas) {
                adapter.setData(datas);
            }

            @Override
            protected void onError(APIException ex) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * 以下都是模拟数据,不用细看。
     */
    private void analogData(List<List<AndroidBean>> lists, GankIoDayBean.ResultsBean results) {
        if (results.getAndroid() != null && results.getAndroid().size() > 0) {
            addUrlList(lists, results.getAndroid(), "Android");
        }
        if (results.getWelfare() != null && results.getWelfare().size() > 0) {
            addUrlList(lists, results.getWelfare(), "福利");
        }
        if (results.getiOS() != null && results.getiOS().size() > 0) {
            addUrlList(lists, results.getiOS(), "IOS");
        }
        if (results.getRestMovie() != null && results.getRestMovie().size() > 0) {
            addUrlList(lists, results.getRestMovie(), "休息视频");
        }
        if (results.getResource() != null && results.getResource().size() > 0) {
            addUrlList(lists, results.getResource(), "拓展资源");
        }
        if (results.getRecommend() != null && results.getRecommend().size() > 0) {
            addUrlList(lists, results.getRecommend(), "瞎推荐");
        }
        if (results.getFront() != null && results.getFront().size() > 0) {
            addUrlList(lists, results.getFront(), "前端");
        }
        if (results.getApp() != null && results.getApp().size() > 0) {
            addUrlList(lists, results.getApp(), "App");
        }
    }

    // subList没有实现序列化！缓存时会出错！
    private void addUrlList(List<List<AndroidBean>> lists, List<AndroidBean> arrayList, String typeTitle) {
        // title
        AndroidBean bean = new AndroidBean();
        bean.setType_title(typeTitle);
        ArrayList<AndroidBean> androidBeen = new ArrayList<>();
        androidBeen.add(bean);
        lists.add(androidBeen);

        int androidSize = arrayList.size();

        if (androidSize > 0 && androidSize < 4) {

            lists.add(addUrlList(arrayList, androidSize));
        } else if (androidSize >= 4) {

            ArrayList<AndroidBean> list1 = new ArrayList<>();
            ArrayList<AndroidBean> list2 = new ArrayList<>();

            for (int i = 0; i < androidSize; i++) {
                if (i < 3) {
                    list1.add(getAndroidBean(arrayList, i, androidSize));
                } else if (i < 6) {
                    list2.add(getAndroidBean(arrayList, i, androidSize));
                }
            }
            lists.add(list1);
            lists.add(list2);
        }
    }

    private List<AndroidBean> addUrlList(List<AndroidBean> arrayList, int androidSize) {
        List<AndroidBean> tempList = new ArrayList<>();
        for (int i = 0; i < androidSize; i++) {
            AndroidBean androidBean = new AndroidBean();
            // 标题
            androidBean.setDesc(arrayList.get(i).getDesc());
            // 类型
            androidBean.setType(arrayList.get(i).getType());
            // 跳转链接
            androidBean.setUrl(arrayList.get(i).getUrl());
            //            DebugUtil.error("---androidSize:  " + androidSize);
            // 随机图的url
            if (androidSize == 1) {
                androidBean.setImage_url(ConstantsImageUrl.HOME_ONE_URLS[getRandom(1)]);//一图
            } else if (androidSize == 2) {
                androidBean.setImage_url(ConstantsImageUrl.HOME_TWO_URLS[getRandom(2)]);//两图
            } else if (androidSize == 3) {
                androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);//三图
            }
            tempList.add(androidBean);
        }
        return tempList;
    }

    private AndroidBean getAndroidBean(List<AndroidBean> arrayList, int i, int androidSize) {

        AndroidBean androidBean = new AndroidBean();
        // 标题
        androidBean.setDesc(arrayList.get(i).getDesc());
        // 类型
        androidBean.setType(arrayList.get(i).getType());
        // 跳转链接
        androidBean.setUrl(arrayList.get(i).getUrl());
        // 随机图的url
        if (i < 3) {
            androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);//三小图
        } else if (androidSize == 4) {
            androidBean.setImage_url(ConstantsImageUrl.HOME_ONE_URLS[getRandom(1)]);//一图
        } else if (androidSize == 5) {
            androidBean.setImage_url(ConstantsImageUrl.HOME_TWO_URLS[getRandom(2)]);//两图
        } else if (androidSize >= 6) {
            androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);//三小图
        }
        return androidBean;
    }

    /**
     * 取不同的随机图，在每次网络请求时重置
     */
    private int getRandom(int type) {
        String saveWhere = null;
        int urlLength = 0;
        if (type == 1) {
            saveWhere = SPUtils.SPkey.HOME_ONE;
            urlLength = ConstantsImageUrl.HOME_ONE_URLS.length;
        } else if (type == 2) {
            saveWhere = SPUtils.SPkey.HOME_TWO;
            urlLength = ConstantsImageUrl.HOME_TWO_URLS.length;
        } else if (type == 3) {
            saveWhere = SPUtils.SPkey.HOME_SIX;
            urlLength = ConstantsImageUrl.HOME_SIX_URLS.length;
        }

        String homeSix = SPUtils.getString(saveWhere, "");
        if (!TextUtils.isEmpty(homeSix)) {
            // 已取到的值
            String[] split = homeSix.split(",");

            Random random = new Random();
            for (int j = 0; j < urlLength; j++) {
                int randomInt = random.nextInt(urlLength);

                boolean isUse = false;
                for (String aSplit : split) {
                    if (!TextUtils.isEmpty(aSplit) && String.valueOf(randomInt).equals(aSplit)) {
                        isUse = true;
                        break;
                    }
                }
                if (!isUse) {
                    StringBuilder sb = new StringBuilder(homeSix);
                    sb.insert(0, randomInt + ",");
                    SPUtils.putString(saveWhere, sb.toString());
                    return randomInt;
                }
            }

        } else {
            Random random = new Random();
            int randomInt = random.nextInt(urlLength);
            SPUtils.putString(saveWhere, randomInt + ",");
            return randomInt;
        }
        return 0;
    }

}
