package com.example.haoping.rxjavademo.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haoping.rxjavademo.R;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBt1;
    private Button mBt2;
    private Button mBt3;
    private Button mBt4;
    private Button mBt5;
    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;
    private ImageView mIv4;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBt1 = (Button) findViewById(R.id.button1);
        mBt2 = (Button) findViewById(R.id.button2);
        mBt3 = (Button) findViewById(R.id.button3);
        mBt4 = (Button) findViewById(R.id.button4);
        mBt5 = (Button) findViewById(R.id.button5);

        mIv1 = (ImageView) findViewById(R.id.iv1);
        mIv2 = (ImageView) findViewById(R.id.iv2);
        mIv3 = (ImageView) findViewById(R.id.iv3);
        mIv4 = (ImageView) findViewById(R.id.iv4);

        mTv = (TextView) findViewById(R.id.tv);

        mBt1.setOnClickListener(this);
        mBt2.setOnClickListener(this);
        mBt3.setOnClickListener(this);
        mBt4.setOnClickListener(this);
        mBt5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                //
                break;
            case R.id.button2:
                //
                break;
            case R.id.button3:
                //
                break;
            case R.id.button4:
                //
                break;
            case R.id.button5:
                //
                break;
        }
    }

    public void rxJava(){
        final String[] strs = {"one", "two", "three", "four", "five"};
        Observable<String[]> observable = Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                subscriber.onNext(strs);
            }
        });


    }
}
