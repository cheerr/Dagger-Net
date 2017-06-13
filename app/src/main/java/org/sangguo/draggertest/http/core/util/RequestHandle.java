package org.sangguo.draggertest.http.core.util;

import android.os.Looper;
import android.support.annotation.NonNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.params.Params;

/**
 * Created by chenwei on 2017/6/8.
 */

public class RequestHandle {

  //get方法异步请求数据
  public static void getURL(OkHttpClient client, String baseUrl, Params params,
      RequestBuilderFilter builderDo, @NonNull ResponseInterface handlerInterface) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      String url = baseUrl + (params == null ? "" : ("?" + params.buildGetUrl()));
      ILog.i("http", url);
      Request.Builder builder = new Request.Builder()
          .url(url);
      if (builderDo != null) {
        builderDo.filter(builder);
      }
      Request request = builder.build();
      client.newCall(request).enqueue(handlerInterface.prepare());
    } else {
      getSyncURL(client, baseUrl, params, builderDo, handlerInterface);
    }
  }

  //get方法同步请求数据
  private static void getSyncURL(OkHttpClient client, String baseUrl, Params params,
      RequestBuilderFilter builderDo, @NonNull ResponseInterface handlerInterface) {

    String url = baseUrl + (params == null ? "" : ("?" + params.buildGetUrl()));

    ILog.i("http", url);

    Request.Builder builder = new Request.Builder()
        .url(url);
    if (builderDo != null) {
      builderDo.filter(builder);
    }
    Request request = builder.build();
    Call call = client.newCall(request);
    handlerInterface.prepare();
    try {
      Response response = call.execute();
      handlerInterface.onResponse(call, response);
    } catch (IOException e) {
      handlerInterface.onFailure(call, e);
    }
  }

  //post方法异步请求数据
  public static void postURL(OkHttpClient client, String baseUrl, Params params,
      RequestBuilderFilter builderDo, @NonNull ResponseInterface handlerInterface) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      ILog.i("http", baseUrl + "?" + params.buildGetUrl());

      Request.Builder builder = params.buildPostBody()
          .url(baseUrl);
      if (builderDo != null) {
        builderDo.filter(builder);
      }
      Request request = builder.build();
      client.newCall(request).enqueue(handlerInterface.prepare());
    } else {
      postSyncURL(client, baseUrl, params, builderDo, handlerInterface);
    }
  }

  //post方法同步请求数据

  private static void postSyncURL(OkHttpClient client, String baseUrl, Params params,
      RequestBuilderFilter builderDo, @NonNull ResponseInterface handlerInterface) {
    ILog.i("http", baseUrl + "?" + params.buildGetUrl());
    Request.Builder builder = params.buildPostBody()
        .url(baseUrl);
    if (builderDo != null) {
      builderDo.filter(builder);
    }
    Request request = builder.build();
    Call call = client.newCall(request);
    handlerInterface.prepare();
    try {
      Response response = call.execute();
      handlerInterface.onResponse(call, response);
    } catch (IOException e) {
      handlerInterface.onFailure(call, e);
    }
  }
}
