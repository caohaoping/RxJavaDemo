package com.example.haoping.rxdemo;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by haoping on 17/3/25.
 * TODO
 */
public class RxFile {

    private ImageView imageCollectorView;

    public RxFile(File folders, ImageView imageCollectorView) {
        this.imageCollectorView = imageCollectorView;

        Observable.from(folders.listFiles())
                .flatMap(new Func1<File, Observable<File>>() {
                    @Override
                    public Observable<File> call(File file) {
                        return Observable.from(file.listFiles());
                    }
                })
                .filter(new Func1<File, Boolean>() {
                    @Override
                    public Boolean call(File file) {
                        return file.getName().endsWith(".png");
                    }
                })
                .map(new Func1<File, Bitmap>() {
                    @Override
                    public Bitmap call(File file) {
                        return getBitmapFromFile(file);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        RxFile.this.imageCollectorView.setImageBitmap(bitmap);
                    }
                });

    }

    private Bitmap getBitmapFromFile(File file) {
        return null;
    }
}
