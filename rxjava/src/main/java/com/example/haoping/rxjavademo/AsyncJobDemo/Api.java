package com.example.haoping.rxjavademo.AsyncJobDemo;

import android.net.Uri;

import java.util.List;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */
public interface Api {

    interface CatsQueryCallback {
        void onCatListReceived(List<Cat> cats);
        void onQueryFailed(Exception e);
    }

    interface StoreCallback{
        void onCatStored(Uri uri);
        void onStoreFailed(Exception e);
    }

    void queryCats(String query, CatsQueryCallback catsQueryCallback);

    void store(Cat cat, StoreCallback storeCallback);

}
