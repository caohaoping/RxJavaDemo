package cn.iome.rxjava2.rxDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by haoping on 17/4/6.
 * TODO
 */
public class RxJack6 {

    public static void main(String[] args) {
        //rx1();
        rx2();
    }

    private static void rx1() {
        final OkHttpClient client = new OkHttpClient.Builder().build();

        final Request request = new Request.Builder()
                .url("https://github.com")
                .build();

        Observable
                .fromCallable(new Callable<Response>() {
                    @Override
                    public Response call() throws Exception {
                        return client.newCall(request).execute();
                    }
                })
                //.subscribeOn(Schedulers.io())
                .map(new Function<Response, String>() {
                    @Override
                    public String apply(@NonNull Response response) throws Exception {
                        return response.body().string();
                    }
                })
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }

    private static void rx2() {

/*        Flowable.fromCallable(() -> "Hello");

        Observable.fromCallable(() -> "Hello");

        Maybe.fromCallable(() -> "Hello");
        Maybe.fromAction(() -> System.out.println("Hello"));
        Maybe.fromRunnable(() -> System.out.println("Hello"))

        Single.fromCallable(() -> "Hello");

        Completable.fromCallable(() -> "Ignored!");
        Completable.fromAction(() -> System.out.println("Hello"));
        Completable.fromRunnable(() -> System.out.println("Hello"));*/

        Flowable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        });

        Flowable.fromFuture(new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public String get(long timeout, @android.support.annotation.NonNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        });

        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        });

        Maybe.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        });

        Maybe.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Hello");
            }
        });

        Maybe.fromRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });

        Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        });

        Completable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Ignored!";
            }
        });

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("Hello");
            }
        });

        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println("Completable-Hello");
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                System.out.print("Completable-start");
            }
        });

    }

}
