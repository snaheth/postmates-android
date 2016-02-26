package com.example.chs.postmates_android;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by snaheth on 10/10/15.
 */
public class TestCallback implements Callback{

    private Activity activity;
    private TextView tv;
    private String msg;

    public TestCallback(String str, Activity anActivity, TextView aTextView){
        activity = anActivity;
        tv = aTextView;
        msg = str;
    }

    @Override
    public void onResponse(Response response) throws IOException {
        final String respStr = response.body().string();
        Log.v(msg, respStr);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(respStr);
            }
        });
    }

    @Override
    public void onFailure(Request request, IOException e) {
        Log.d("Error with Postmates API" , e.toString());
    }
}