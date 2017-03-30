package com.example.haoping.rxjavademo.ASyncDemo;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public class QueryApi implements Api {

    @Override
    public void queryCats(String query, CatsQueryCallback catsQueryCallback) {
        List<Cat> cats = new ArrayList<>();
        try {
            if(cats != null && cats.size() != 0){
                catsQueryCallback.onCatListReceived(cats);
            }
        } catch (Exception e) {
            e.printStackTrace();
            catsQueryCallback.onQueryFailed(e);
        }
    }

    @Override
    public Uri store(Cat cat) {
        return null;
    }

}
