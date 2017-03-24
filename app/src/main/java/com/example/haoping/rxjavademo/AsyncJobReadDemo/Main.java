package com.example.haoping.rxjavademo.AsyncJobReadDemo;

import android.net.Uri;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public class Main {
    public static void main(String[] args){
        CatsHelper catsHelper = new CatsHelper();
        AsyncJob<Uri> asyncJob = catsHelper.saveTheCutestCat("tom");
        asyncJob.start(new CallBack<Uri>() {
            @Override
            public void onResult(Uri result) {

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }
}
