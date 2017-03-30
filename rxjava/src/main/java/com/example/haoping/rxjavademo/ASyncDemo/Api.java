package com.example.haoping.rxjavademo.ASyncDemo;

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

    void queryCats(String query, CatsQueryCallback catsQueryCallback);

    Uri store(Cat cat);

}
