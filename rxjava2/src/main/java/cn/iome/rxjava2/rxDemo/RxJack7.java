package cn.iome.rxjava2.rxDemo;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by haoping on 17/4/6.
 * TODO
 */
public class RxJack7 {

    public static void main(String[] args) {
        rx1();
    }

    private static void rx1() {
        final OkHttpClient client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder().url("https://baidu.com").build();

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> e) throws Exception {
                final Call call = client.newCall(request);
                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        call.cancel();
                    }
                });
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException exception) {
                        e.onError(exception);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        e.onNext(response.body().string());
                        e.onComplete();
                    }
                });
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe: " + d.isDisposed());
            }

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
                System.out.println("complete");
            }
        });

    }

}
