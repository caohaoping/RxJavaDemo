package com.example.haoping.rxjavademo.AsyncJobReadDemo;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public abstract class AsyncJob<T> {

    public abstract void start(CallBack<T> callBack);

}
