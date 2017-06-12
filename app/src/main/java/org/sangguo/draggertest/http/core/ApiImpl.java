package org.sangguo.draggertest.http.core;

import android.support.annotation.NonNull;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import org.json.JSONObject;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.core.annotation.Get;
import org.sangguo.draggertest.http.core.annotation.Post;
import org.sangguo.draggertest.http.core.configuration.ApiConfiguration;
import org.sangguo.draggertest.http.core.configuration.ApiConfigurationImpl;
import org.sangguo.draggertest.http.core.model.AnnotationMsg;
import org.sangguo.draggertest.http.core.type.ReqType;
import org.sangguo.draggertest.http.core.util.RequestBuilderDeal;
import org.sangguo.draggertest.http.core.util.RequestHandle;
import org.sangguo.draggertest.http.data.Result;
import org.sangguo.draggertest.http.data.SimpleResult;
import org.sangguo.draggertest.http.params.Params;

/**
 * Created by chenwei on 2017/6/8.
 */

public class ApiImpl implements ApiInterface, ParamDealInterface, JsonChangeInterface {

  private ApiConfiguration apiConfiguration;

  private AnnotationMsg msg;

  public ApiImpl() {
    apiConfiguration = getApiConfiguration();
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

  @Override public ApiConfiguration getApiConfiguration() {
    return ApiConfigurationImpl.DEFAULT;
  }

  @Override public String baseUrl() {
    return apiConfiguration.baseUrl();
  }

  @Override public String apiName() {
    return msg.api;
  }

  @Override public OkHttpClient httpClient() {
    return apiConfiguration.httpClient();
  }

  @Override public ReqType reqType() {
    return msg.reqType;
  }

  @Override public boolean cache() {
    return true;
  }

  @Override public void encryptParams(Params params) {

  }

  @Override public RequestBuilderDeal requestBuilderDeal() {
    return null;
  }

  @Override public final void request(Params params, ResponseInterface responseInterface) {

    //配置参数获取
    OkHttpClient client = httpClient();
    RequestBuilderDeal builderDeal = requestBuilderDeal();
    String baseUrl = baseUrl();

    //参数处理
    encryptParams(params);
    switch (reqType()) {
      case GET:
        RequestHandle.getURL(client, baseUrl, params, builderDeal, responseInterface);
        break;
      case POST:
        RequestHandle.postURL(client, baseUrl, params, builderDeal, responseInterface);
        break;
    }
  }

  /**
   * JSONObject 转换为Result的实现方式
   */
  @Override @NonNull public Result jsonToResult(JSONObject json) {
    return new SimpleResult(json);
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
