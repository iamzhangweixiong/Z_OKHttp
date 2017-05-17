package com.zhangwx.z_okhttp;

import java.io.IOException;

import android.os.Handler;
import android.os.Looper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by zhangwx on 2017/5/16.
 * helper
 */

public class OKHttpHelper {

    public OKHttpHelper() {
    }

    public void get(final ResponseListener listener) {
        BackgroundThread.post(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.baidu.com").build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        final ResponseBody body = response.body();



                        listener.onResponse(body.string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface ResponseListener {
        void onResponse(String result);
    }
}
