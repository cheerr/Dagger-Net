package org.sangguo.draggertest.http.core.build;

import okhttp3.OkHttpClient;
import org.sangguo.draggertest.http.core.build.okhttpclient.SimpleHttpClient;

/**
 * 默认为ApiImpl 提供请求公有部分
 * Created by chenwei on 2017/6/8.
 */
public class ApiBuild {

  public static ApiBuild DEFAULT = new ApiBuild();

  private ApiBuild() {
  }

  public String baseUrl() {
    return "http://www.baidu.com";
  }

  public OkHttpClient httpClient() {
    return new SimpleHttpClient().get();
  }
}
