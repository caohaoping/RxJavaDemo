package cn.iome.rxjava2.rxjava2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RxDemo {

    public static void main(String[] args) {
        rx1();
        rxFlowable();
        rxObservable();
    }

    private static void rx1() {

    }

    private static void rxFlowable() {

        Flowable<String> stringFlowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("how ");
                e.onNext("are ");
                e.onNext("you ");
                e.onComplete();

            }
        }, BackpressureStrategy.BUFFER);

        stringFlowable.subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.print("requestBefore ");
                s.request(Long.MAX_VALUE);
                System.out.print("requestAfter ");
            }

            @Override
            public void onNext(String s) {
                System.out.print(s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.print(t.toString());
            }

            @Override
            public void onComplete() {
                System.out.print("complete \n");
            }
        });

    }

    private static void rxObservable() {

        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("how ");
                e.onNext("are ");
                e.onNext("you ");
                e.onComplete();
            }
        });

        stringObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.print(d.isDisposed() + " ");
            }

            @Override
            public void onNext(String s) {
                System.out.print(s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.print(e.toString());
            }

            @Override
            public void onComplete() {
                System.out.print("complete \n");
            }
        });

    }

}
