package com.zhangwx.z_okhttp;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhangwx on 2017/5/16.
 * helper
 */

public class OKHttpHelper {

    private String BASE_URL = "http://cr.m.ksmobile.net/news/fresh?";
    private String URL = BASE_URL + "media_info=tw%2Cytb%2Cgif2%2Cig&act=1&scenario=0x001d1901&lan=zh_CN&osv=6.0.1&appv=1.0&ch=0&pid=6&gallery=1&action=0xc028f&offset=&count=10&pf=android&net=wifi&v=4&mnc=01&ctype=0x207&display=0x1e0018f&mode=1&brand=google&mcc=310&nmnc=01&nmcc=310&aid=6tghy78354wfr54&model=Nexus+5";
    private Gson gson;

    public OKHttpHelper() {
        gson = new Gson();
    }

    public void get(final ResponseListener listener) {
        BackgroundThread.post(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(URL).build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
//                        final ResponseBody body = response.body();
                        NewBean newBean = gson.fromJson(response.body().charStream(), NewBean.class);
                        listener.onResponse(newBean.getData().get(1).getOriginalurl());
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
