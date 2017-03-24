package com.example.haoping.rxjavademo.RxJavaSimpleDemo;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */
public interface Func<T, R> {
    R call(T t);
}
