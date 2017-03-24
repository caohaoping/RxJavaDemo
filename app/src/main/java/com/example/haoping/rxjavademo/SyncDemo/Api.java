package com.example.haoping.rxjavademo.SyncDemo;

import android.net.Uri;

import java.util.List;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */
public interface Api {

    List<Cat> queryCats(String query);
    Uri store(Cat cat);

}
