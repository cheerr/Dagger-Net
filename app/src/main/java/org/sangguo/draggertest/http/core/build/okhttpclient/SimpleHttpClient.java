package org.sangguo.draggertest.http.core.build.okhttpclient;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by chenwei on 2017/6/8.
 */

public class SimpleHttpClient implements OkHttpClientInterface {

  private OkHttpClient okHttpClient;

  @Override public long connectTimeout() {
    return 30;
  }

  @Override public long readTime() {
    return 30;
  }

  @Override public long writeTime() {
    return 30;
  }

  @Override public List<Interceptor> interceptorList() {
    return Collections.<Interceptor>singletonList(new ContentTypeInterceptorImpl());
  }

  @Override public List<ConnectionSpec> connectionSpecList() {
    ConnectionSpec spec =
        new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).allEnabledTlsVersions()
            .allEnabledCipherSuites()
            .build();
    return Collections.singletonList(spec);
  }

  public SimpleHttpClient() {
    okHttpClient =
        create(connectionSpecList(), interceptorList(), connectTimeout(), readTime(), writeTime());
  }

  public OkHttpClient get() {
    return okHttpClient;
  }

  /**
   * 默认实现
   */
  private OkHttpClient create(final List<ConnectionSpec> connectionSpecs,
      final List<Interceptor> interceptors, final long connectTimeout, final long readTimeout,
      final long writeTimeout) {
    OkHttpClient.Builder builder =
        new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS);

    if (connectionSpecs != null && !connectionSpecs.isEmpty()) {
      builder.connectionSpecs(connectionSpecs);
    }

    builder.addNetworkInterceptor(new StethoInterceptor());
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    builder.addInterceptor(logging);

    if (interceptors != null && !interceptors.isEmpty()) {
      for (Interceptor interceptor : interceptors) {
        builder.addInterceptor(interceptor);
      }
    }

    return builder.build();
  }
}
