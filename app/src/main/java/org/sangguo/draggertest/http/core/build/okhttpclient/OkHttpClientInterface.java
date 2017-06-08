package org.sangguo.draggertest.http.core.build.okhttpclient;

import java.util.List;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;

/**
 * Created by chenwei on 2017/6/8.
 */

public interface OkHttpClientInterface {

  public long connectTimeout();

  public long readTime();

  public long writeTime();

  public List<Interceptor> interceptorList();

  public List<ConnectionSpec> connectionSpecList();
}
