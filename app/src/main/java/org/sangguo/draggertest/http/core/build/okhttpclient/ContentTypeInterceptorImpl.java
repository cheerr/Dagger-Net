
package org.sangguo.draggertest.http.core.build.okhttpclient;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class ContentTypeInterceptorImpl implements Interceptor {
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String APPLICATION_JSON = "application/json;charset=utf-8";

  @Override public Response intercept(Chain chain) throws IOException {
    final Request.Builder builder = chain.request().newBuilder();
    builder.addHeader(CONTENT_TYPE, APPLICATION_JSON);
    final Request request = builder.build();
    return chain.proceed(request);
  }
}