package com.example.haoping.rxjavademo.ASyncDemo;

import android.graphics.Bitmap;

/**
 * @Author haoping
 * @Date 2016/12/5
 * @Des TODO
 */
public class Cat implements Comparable<Cat> {

    Bitmap image;
    int cuteness;

    @Override
    public int compareTo(Cat another) {
        return Integer.compare(cuteness, another.cuteness);
    }

}