package org.sangguo.draggertest.http.core.configuration;

import okhttp3.OkHttpClient;
import org.sangguo.draggertest.http.core.configuration.okhttpclient.SimpleHttpClient;

/**
 * Created by chenwei on 2017/6/9.
 */

public class ApiConfigurationImpl implements ApiConfiguration {

  public static ApiConfiguration DEFAULT = new ApiConfigurationImpl();

  private ApiConfigurationImpl() {
  }

  @Override
  public String baseUrl() {
    return "http://www.baidu.com/";
  }

  @Override
  public OkHttpClient httpClient() {
    return new SimpleHttpClient().get();
  }
}
