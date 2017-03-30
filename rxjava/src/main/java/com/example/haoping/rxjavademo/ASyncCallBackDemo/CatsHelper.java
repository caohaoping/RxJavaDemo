package com.example.haoping.rxjavademo.ASyncCallBackDemo;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */
public class CatsHelper {

    ApiWrapper apiWrapper;

    public void saveTheCutestCat(String query, final CallBack<Uri> cutestCatCallback){
        apiWrapper.queryCats(query, new CallBack<List<Cat>>() {
            @Override
            public void onResult(List<Cat> result) {
                Cat cutest = findCutest(result);
                apiWrapper.store(cutest, cutestCatCallback);
            }

            @Override
            public void onError(Exception e) {
                cutestCatCallback.onError(e);
            }
        });
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
