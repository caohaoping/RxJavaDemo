package cn.iome.rxjava2.rxDemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.Callable;

import cn.iome.rxjava2.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by haoping on 17/4/6.
 * TODO
 */
public class RxJack5 extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rx1();
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
                .subscribeOn(Schedulers.io())
                .map(new Function<Response, String>() {
                    @Override
                    public String apply(@NonNull Response response) throws Exception {
                        return response.body().string();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println(s);
                    }
                });
    }
}
