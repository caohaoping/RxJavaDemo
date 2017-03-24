package com.example.haoping.rxjavademo.AsyncJobReadDemo;

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
        final AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        final AsyncJob<Cat> cutestCatAsyncJob = new AsyncJob<Cat>(){
            @Override
            public void start(final CallBack callBack) {
                catsListAsyncJob.start(new CallBack<List<Cat>>() {
                    @Override
                    public void onResult(List<Cat> result) {
                        callBack.onResult(findCutest(result));
                    }

                    @Override
                    public void onError(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
        AsyncJob<Uri> storedUriAsyncJob = new AsyncJob<Uri>(){
            @Override
            public void start(final CallBack<Uri> callBack) {
                cutestCatAsyncJob.start(new CallBack<Cat>(){
                    @Override
                    public void onResult(Cat result) {
                        apiWrapper.save(result)
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
        return storedUriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
