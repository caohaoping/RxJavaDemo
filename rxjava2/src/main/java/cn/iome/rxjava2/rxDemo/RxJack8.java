package cn.iome.rxjava2.rxDemo;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by haoping on 17/4/6.
 * TODO
 */
public class RxJack8 {

    public static void main(String[] args){
        rx1();
        rx2();
    }

    private static void rx1() {
        Observable<String> o = Observable.just("hello");
        o.subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error: " + e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private static void rx2() {
        Observable<String> o = Observable.just("hello");
        o.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Maybe<String> m = Maybe.just("hello");
        m.subscribeWith(new DisposableMaybeObserver<String>() {
            @Override
            public void onSuccess(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Single<String> s = Single.just("hello");
        s.subscribeWith(new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

        Completable c = Completable.complete();
        c.subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

        Flowable<String> f = Flowable.just("hello");
        f.subscribeWith(new DisposableSubscriber<String>() {
            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
