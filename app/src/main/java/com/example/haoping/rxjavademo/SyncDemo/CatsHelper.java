package com.example.haoping.rxjavademo.SyncDemo;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */
public class CatsHelper {

    Api api;

    public Uri saveTheCutestCat(String query){
        try {
            List<Cat> cats = api.queryCats(query);
            Cat cutest = findCutest(cats);
            Uri savedUri = api.store(cutest);
            return savedUri;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }

}
