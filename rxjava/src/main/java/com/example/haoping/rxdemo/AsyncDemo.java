package com.example.haoping.rxdemo;

/**
 * Created by haoping on 17/4/1.
 * TODO
 */
public class AsyncDemo {

    public static void main(String[] args) {

        new AsyncJob<String>() {
            @Override
            void onStart(Callback<String> callback) {
                System.out.print("AsyncJob-onStart\n");
                callback.onSuccess(String.valueOf(1));
                System.out.print("AsyncJob-onEnd\n");
            }
        }.map(new Func<String, String>() {
            @Override
            public String call(String s) {
                return "sss---" + s + "---sss";
            }
        }).onStart(new Callback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.print(s + "\n");
            }

            @Override
            public void onFailed(Exception e) {
                System.out.print(e.toString() + "\n");
            }
        });

        new AsyncJob<Integer>() {
            @Override
            void onStart(Callback<Integer> callback) {
                System.out.print("AsyncJob-onStart1\n");
                callback.onSuccess(12);
                System.out.print("AsyncJob-onEnd1\n");
            }
        }.flatMap(new Func<Integer, AsyncJob<String>>() {
            @Override
            public AsyncJob<String> call(final Integer integer) {
                return new AsyncJob<String>() {
                    @Override
                    void onStart(Callback<String> callback) {
                        System.out.print("AsyncJob-onStart2\n");
                        callback.onSuccess(String.valueOf(integer) + 3);
                        System.out.print("AsyncJob-onEnd2\n");
                    }
                };
            }
        }).map(new Func<String, Integer>() {
            @Override
            public Integer call(String s) {
                return Integer.parseInt(s + 4);
            }
        }).filter(new Func<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return true;
            }
        }).onStart(new Callback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                System.out.print(integer + "\n");
            }

            @Override
            public void onFailed(Exception e) {
                System.out.print(e.toString() + "\n");
            }
        });

    }

    public abstract static class AsyncJob<T> {

        abstract void onStart(Callback<T> callback);

        <R> AsyncJob<R> map(final Func<T, R> func) {
            final AsyncJob<T> source = this;
            return new AsyncJob<R>() {
                @Override
                void onStart(final Callback<R> callback) {
                    source.onStart(new Callback<T>() {
                        @Override
                        public void onSuccess(T t) {
                            R r = func.call(t);
                            callback.onSuccess(r);
                        }

                        @Override
                        public void onFailed(Exception e) {
                            callback.onFailed(e);
                        }
                    });
                }
            };
        }

        <R> AsyncJob<R> flatMap(final Func<T, AsyncJob<R>> func) {
            final AsyncJob<T> source = this;
            return new AsyncJob<R>() {
                @Override
                void onStart(final Callback<R> callback) {
                    source.onStart(new Callback<T>() {
                        @Override
                        public void onSuccess(T t) {
                            AsyncJob<R> mapped = func.call(t);
                            mapped.onStart(new Callback<R>() {
                                @Override
                                public void onSuccess(R r) {
                                    callback.onSuccess(r);
                                }

                                @Override
                                public void onFailed(Exception e) {
                                    callback.onFailed(e);
                                }
                            });
                        }

                        @Override
                        public void onFailed(Exception e) {
                            callback.onFailed(e);
                        }
                    });
                }
            };
        }

        AsyncJob<T> filter(final Func<T, Boolean> func){
            final AsyncJob<T> source = this;
            return new AsyncJob<T>() {
                @Override
                void onStart(final Callback<T> callback) {
                    source.onStart(new Callback<T>() {
                        @SuppressWarnings("unchecked")
                        @Override
                        public void onSuccess(T t) {
                            boolean result = func.call(t);
                            if(result){
                                callback.onSuccess(t);
                            }
                        }

                        @Override
                        public void onFailed(Exception e) {
                            callback.onFailed(e);
                        }
                    });
                }
            };
        }

    }

    interface Callback<T> {
        void onSuccess(T t);

        void onFailed(Exception e);
    }

    interface Func<T, R> {
        R call(T t);
    }
}
