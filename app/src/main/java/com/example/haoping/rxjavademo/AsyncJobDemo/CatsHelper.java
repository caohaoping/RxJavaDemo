package com.example.haoping.rxjavademo.AsyncJobDemo;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */
public class CatsHelper {

    ApiWrapper apiWrapper;

    public AsyncJob<Uri> saveTheCutestCat(final String query){
        return new AsyncJob<Uri>() {
            @Override
            public void start(final CallBack<Uri> callBack) {
                apiWrapper.queryCats(query)
                        .start(new CallBack<List<Cat>>() {
                            @Override
                            public void onResult(List<Cat> result) {
                                Cat cutest = findCutest(result);
                                apiWrapper.save(cutest)
                                        .start(new CallBack<Uri>() {
                                            @Override
                                            public void onResult(Uri result) {
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

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
