package com.zhangwx.z_okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangwx on 2017/5/16.
 *
 */

public class OKHttpHelper {

    public interface OnResponse{

    }


    public String get() {

        BackgroundThread.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return request.body().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }
}
