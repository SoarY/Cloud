package com.soar.cloud.retrofit;


import com.soar.cloud.bean.AndroidPlayBannerBean;
import com.soar.cloud.bean.DataBean;
import com.soar.cloud.bean.GankIoDataBean;
import com.soar.cloud.bean.GankIoDayBean;
import com.soar.cloud.bean.HotMovieBean;
import com.soar.cloud.bean.NaviBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * YONG_
 */
public interface API {

    //---------------------------------GankIo-API---------------------------------//

    /**
     * 每日推荐
     * eg:http://gank.io/api/day/2015/08/06
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankIoDayBean> getGankIoDay(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
    Observable<GankIoDataBean> getGankIoData(@Path("type") String type, @Path("page") int page, @Path("pre_page") int pre_page);


    //---------------------------------PlayAndroid-API---------------------------------//

    /**
     * 玩安卓轮播图
     */
    @GET("banner/json")
    Observable<BaseResultBean<List<AndroidPlayBannerBean>>> getAndroidPlayBanner();

    /**
     * 玩安卓，文章列表、知识体系下的文章
     *
     * @param page 页码，从0开始
     * @param cid  体系id
     */
    @GET("article/list/{page}/json")
    Observable<BaseResultBean<DataBean>> getHomeList(@Path("page") int page, @Query("cid") Integer cid);

    /**
     * 导航数据
     */
    @GET("navi/json")
    Observable<BaseResultBean<List<NaviBean>>> getNaviJson();


    //---------------------------------DOUBAN-API---------------------------------//

    /**
     * 豆瓣热映电影，每日更新
     */
    @GET("v2/movie/in_theaters")
    Observable<HotMovieBean> getHotMovie();
}
