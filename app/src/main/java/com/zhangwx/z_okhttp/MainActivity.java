package com.zhangwx.z_okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private OKHttpHelper mOkHttpHelper;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOkHttpHelper = new OKHttpHelper();
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOkHttpHelper.get(new OKHttpHelper.ResponseListener() {
                    @Override
                    public void onResponse(final String result) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                TextView textView = (TextView) findViewById(R.id.content);
                                textView.setText(result);
                            }
                        });
                    }
                });
            }
        });
    }
}
