package com.example.haoping.rxjavademo.RxJavaSimpleDemo;

import android.net.Uri;

import java.util.List;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public class ApiWrapper {

    Api api;

    public AsyncJob<List<Cat>> queryCats(final String query){
        return new AsyncJob<List<Cat>>() {
            @Override
            public void start(final CallBack<List<Cat>> callBack) {
                api.queryCats(query, new Api.CatsQueryCallback() {
                    @Override
                    public void onCatListReceived(List<Cat> cats) {
                        callBack.onResult(cats);
                    }

                    @Override
                    public void onQueryFailed(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }

    public AsyncJob<Uri> save(final Cat cat){
        return new AsyncJob<Uri>() {
            @Override
            public void start(final CallBack<Uri> callBack) {
                api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onCatStored(Uri uri) {
                        callBack.onResult(uri);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }


}
