package com.example.haoping.rxjavademo.ASyncDeeperDemo;

import android.net.Uri;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public class Main {

    public static void main(String[] args){
        CatsHelper catsHelper = new CatsHelper();
        catsHelper.saveTheCutestCat("tom", new CatsHelper.CutestCatCallback() {
            @Override
            public void onCutestCatSaved(Uri uri) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

}
