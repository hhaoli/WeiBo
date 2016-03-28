package com.lihonghao.weibo.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.lihonghao.weibo.MyApplication;
import com.squareup.otto.Bus;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class UnauthorisedInterceptor implements Interceptor {
    @Inject
    Bus eventBus;

    public UnauthorisedInterceptor(Context context) {
        MyApplication.get(context).getApplicationComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == 401) {
            new Handler(Looper.getMainLooper()).post(() -> {
                eventBus.post(new BusEvent.AuthenticationError());
            });
        }
        return response;
    }
}
