package com.example.haoping.rxjavademo.ASyncCallBackDemo;

import android.net.Uri;

import java.util.List;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public class ApiWrapper {

    Api api;

    public void queryCats(String query, final CallBack<List<Cat>> catsQueryCallBack){
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                catsQueryCallBack.onResult(cats);
            }

            @Override
            public void onQueryFailed(Exception e) {
                catsQueryCallBack.onError(e);
            }
        });
    }

    public void store(Cat cat, final CallBack<Uri> storeCallBack){
        api.store(cat, new Api.StoreCallback() {
            @Override
            public void onCatStored(Uri uri) {
                storeCallBack.onResult(uri);
            }

            @Override
            public void onStoreFailed(Exception e) {
                storeCallBack.onError(e);
            }
        });
    }

}
