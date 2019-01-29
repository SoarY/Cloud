package com.soar.cloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jingbin on 2016/11/24.
 */

public class GankIoDayBean implements Serializable {

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public static class ResultsBean {
        /**
         * _id : 56cc6d23421aa95caa707a69
         * createdAt : 2015-08-06T07:15:52.65Z
         * desc : 类似Link Bubble的悬浮式操作设计
         * images : ["http://img.gank.io/2f0b6c5f-6de7-4ba3-94ad-98bf721ee447"]
         * source : web
         * publishedAt : 2015-08-07T03:57:48.45Z
         * type : Android
         * url : https://github.com/recruit-lifestyle/FloatingView
         * used : true
         * who : mthli
         */

        private List<AndroidBean> Android;

        private List<AndroidBean> iOS;

        private List<AndroidBean> front;

        private List<AndroidBean> app;

        private List<AndroidBean> 休息视频;

        private List<AndroidBean> resource;

        private List<AndroidBean> recommend;

        private List<AndroidBean> welfare;


        public List<AndroidBean> getAndroid() {
            return Android;
        }

        public List<AndroidBean> getiOS() {
            return iOS;
        }

        public List<AndroidBean> getRestMovie() {
            return 休息视频;
        }

        public List<AndroidBean> getResource() {
            return resource;
        }

        public List<AndroidBean> getRecommend() {
            return recommend;
        }

        public List<AndroidBean> getWelfare() {
            return welfare;
        }

        public List<AndroidBean> getFront() {
            return front;
        }

        public List<AndroidBean> getApp() {
            return app;
        }
    }

    public boolean isError() {
        return error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public List<String> getCategory() {
        return category;
    }
}
