package com.zhangwx.z_okhttp;

import java.io.IOException;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

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
//                        final ResponseBody body = response.body();
                        listener.onResponse(response.message());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void post(final ResponseListener listener) {
        BackgroundThread.post(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                FormBody.Builder builder = new FormBody.Builder();
//                builder.add()
                RequestBody requestBody = builder.build();

                Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .post(requestBody)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        listener.onResponse(response.message());
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
