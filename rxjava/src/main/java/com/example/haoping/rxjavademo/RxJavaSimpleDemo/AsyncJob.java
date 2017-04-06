package com.example.haoping.rxjavademo.RxJavaSimpleDemo;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */

public abstract class AsyncJob<T> {

    public abstract void start(CallBack<T> callBack);

    public <R> AsyncJob<R> map(final Func1<T, R> func){
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

    public <R> AsyncJob<R> flatMap(final Func1<T, AsyncJob<R>> func){
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


    public static <T, R> FuncN<R> fromFunc1(final Func1<T, R> f){
        return new FuncN<R>() {

            @Override
            public R call(Object... args) {
                return f.call((T)args[0]);
            }

        };
    }

    public static <T0, R> FuncN<R> fromFunc(final Func1<? super T0, ? extends R> f) {

        FuncN<Integer> integerFuncN = fromFunc1(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return Integer.getInteger(s);
            }
        });

        return new FuncN<R>() {

            @SuppressWarnings("unchecked")
            @Override
            public R call(Object... args) {
                return f.call((T0) args[0]);
            }

        };

    }

}
