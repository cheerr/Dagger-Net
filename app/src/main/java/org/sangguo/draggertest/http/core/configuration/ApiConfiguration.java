package org.sangguo.draggertest.http.core.configuration;

import okhttp3.OkHttpClient;

/**
 * 默认为ApiImpl 提供请求公有部分
 * Created by chenwei on 2017/6/8.
 */
public interface ApiConfiguration {

  public String baseUrl();

  public OkHttpClient httpClient();
}
