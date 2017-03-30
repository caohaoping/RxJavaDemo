package com.example.haoping.rxjavademo.AsyncJobReadDemo;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public interface CallBack<T> {
    void onResult(T result);
    void onError(Exception e);
}
