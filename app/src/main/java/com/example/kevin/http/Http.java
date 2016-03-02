package com.example.kevin.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kevin on 2016/3/2.
 */
public class Http {
    public static void sendHttpRequest(final String address, final HttpCallbackListener httpCallbackListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    //获取输入流
                    InputStream inputStream = httpURLConnection.getInputStream();
                    //读取输入流
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    //把输入流转化成string
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    if (httpCallbackListener != null) {
                        //回调onFinish方法
                        httpCallbackListener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (httpCallbackListener != null)
                        //回调onError方法
                        httpCallbackListener.onError(e);
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }

    public interface HttpCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }

}
