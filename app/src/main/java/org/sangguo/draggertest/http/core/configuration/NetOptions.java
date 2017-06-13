package org.sangguo.draggertest.http.core.configuration;

import okhttp3.OkHttpClient;
import org.sangguo.draggertest.http.core.JsonChanger;
import org.sangguo.draggertest.http.core.util.RequestBuilderFilter;

/**
 * Created by chenwei on 2017/6/13.
 */

public class NetOptions {

  private String baseUrl;

  private OkHttpClient okHttpClient;

  private RequestBuilderFilter requestBuilderFilter;

  private JsonChanger jsonChanger;

  public String getBaseUrl() {
    return baseUrl;
  }

  public OkHttpClient getOkHttpClient() {
    return okHttpClient;
  }

  public RequestBuilderFilter getRequestBuilderFilter() {
    return requestBuilderFilter;
  }

  public JsonChanger getJsonChanger() {
    return jsonChanger;
  }

  public NetOptions setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
    return this;
  }

  public NetOptions setOkHttpClient(OkHttpClient okHttpClient) {
    this.okHttpClient = okHttpClient;
    return this;
  }

  public NetOptions setRequestBuilderFilter(
      RequestBuilderFilter requestBuilderDeal) {
    this.requestBuilderFilter = requestBuilderDeal;
    return this;
  }

  public NetOptions setJsonChanger(JsonChanger jsonChanger) {
    this.jsonChanger = jsonChanger;
    return this;
  }
}
