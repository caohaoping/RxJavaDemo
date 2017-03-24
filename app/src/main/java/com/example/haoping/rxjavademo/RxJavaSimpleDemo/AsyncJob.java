package com.example.haoping.rxjavademo.RxJavaSimpleDemo;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public abstract class AsyncJob<T> {

    public abstract void start(CallBack<T> callBack);

    public <R> AsyncJob<R> map(final Func<T, R> func){
        final AsyncJob<T> source = this;
        return new AsyncJob<R>() {
            @Override
            public void start(final CallBack<R> callBack) {
                source.start(new CallBack<T>() {
                    @Override
                    public void onResult(T result) {
                        R mapped = func.call(result);
                        callBack.onResult(mapped);
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }

    public <R> AsyncJob<R> flatMap(final Func<T, AsyncJob<R>> func){
        final AsyncJob<T> source = this;
        return new AsyncJob<R>() {
            @Override
            public void start(final CallBack<R> callBack) {
                source.start(new CallBack<T>() {
                    @Override
                    public void onResult(T result) {
                        AsyncJob<R> mapped = func.call(result);
                        mapped.start(new CallBack<R>() {
                            @Override
                            public void onResult(R result) {
                                callBack.onResult(result);
                            }

                            @Override
                            public void onError(Exception e) {
                                callBack.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }

}
