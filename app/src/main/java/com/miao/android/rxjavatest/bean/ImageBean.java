package com.miao.android.rxjavatest.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */

public class ImageBean {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Result {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
