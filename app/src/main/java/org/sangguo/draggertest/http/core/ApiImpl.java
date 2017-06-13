package org.sangguo.draggertest.http.core;

import android.support.annotation.NonNull;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import org.json.JSONObject;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.core.annotation.Get;
import org.sangguo.draggertest.http.core.annotation.Post;
import org.sangguo.draggertest.http.core.configuration.NetOptions;
import org.sangguo.draggertest.http.core.model.AnnotationMsg;
import org.sangguo.draggertest.http.core.type.ReqType;
import org.sangguo.draggertest.http.core.util.RequestBuilderFilter;
import org.sangguo.draggertest.http.core.util.RequestHandle;
import org.sangguo.draggertest.http.data.Result;
import org.sangguo.draggertest.http.params.Params;

/**
 * Created by chenwei on 2017/6/8.
 */

public class ApiImpl implements ApiInterface, ParamFilter, JsonChanger {

  public static NetOptions optionsDefault;

  private NetOptions options = optionsDefault;

  private AnnotationMsg msg;

  public ApiImpl() {
    analyseAnnotation();
  }

  /**
   * 分析注解
   */
  private void analyseAnnotation() {

    msg = new AnnotationMsg();

    Class<?> clazz = getClass();

    if (clazz.getAnnotations() != null) {
      if (clazz.isAnnotationPresent(Get.class)) {
        Get inject = clazz.getAnnotation(Get.class);
        msg.reqType = ReqType.GET;
        msg.api = inject.value();
      }
      if (clazz.isAnnotationPresent(Post.class)) {
        Post inject = clazz.getAnnotation(Post.class);
        msg.reqType = ReqType.POST;
        msg.api = inject.value();
      }
    }
  }

  @Override public String baseUrl() {
    return options.getBaseUrl();
  }

  @Override public String apiName() {
    return msg.api;
  }

  @Override public OkHttpClient httpClient() {
    OkHttpClient okHttpClient = options.getOkHttpClient();
    if (okHttpClient == null) {
      okHttpClient = new OkHttpClient();
    }
    return okHttpClient;
  }

  @Override public ReqType reqType() {
    return msg.reqType;
  }

  @Override public boolean cache() {
    return true;
  }

  @Override public void encryptParams(Params params) {

  }

  @Override public RequestBuilderFilter requestBuilderFilter() {
    return options.getRequestBuilderFilter();
  }

  @Override public final void request(Params params, ResponseInterface responseInterface) {

    if (options == null) {
      options = optionsDefault;
    }

    //配置参数获取
    OkHttpClient client = httpClient();
    RequestBuilderFilter builderDeal = requestBuilderFilter();

    String url = mergeUrl(baseUrl(), apiName());

    //参数处理
    encryptParams(params);
    switch (reqType()) {
      case GET:
        RequestHandle.getURL(client, url, params, builderDeal, responseInterface);
        break;
      case POST:
        RequestHandle.postURL(client, url, params, builderDeal, responseInterface);
        break;
    }
  }

  /**
   * 暴力合并url
   */
  private String mergeUrl(String a, String b) {
    String c = a + "/" + b;
    while (c.contains("//")) {
      c = c.replace("//", "/");
    }
    return c;
  }

  /**
   * JSONObject 转换为Result的实现方式
   */
  @Override @NonNull public Result jsonToResult(JSONObject json) {
    return options.getJsonChanger().jsonToResult(json);
  }

  /**
   * 适用于RxJava回调方式
   * 自定义数据解析，将原始的JSONObject解析成T类型数据
   */
  @Override public <T> T dataParse(JSONObject json, Class<T> clz) {

    Result result = jsonToResult(json);

    if (result.data() == null) return null;
    return JSON.parseObject(result.data(), clz);
  }
}
