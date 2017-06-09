package org.sangguo.draggertest.http.core;

import android.support.annotation.NonNull;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import org.json.JSONObject;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.core.build.ApiBuild;
import org.sangguo.draggertest.http.core.type.ReqType;
import org.sangguo.draggertest.http.core.util.RequestBuilderDeal;
import org.sangguo.draggertest.http.core.util.RequestHandle;
import org.sangguo.draggertest.http.data.Result;
import org.sangguo.draggertest.http.data.SimpleResult;
import org.sangguo.draggertest.http.params.Params;

/**
 * Created by chenwei on 2017/6/8.
 */

public abstract class ApiImpl implements ApiInterface {

  private ApiBuild apiBuild;

  //统一由反射实现
  public ApiImpl() {
    this(ApiBuild.DEFAULT);
  }

  //暂时用不到
  private ApiImpl(ApiBuild apiBuild) {
    this.apiBuild = apiBuild == null ? ApiBuild.DEFAULT : apiBuild;
  }

  @Override public String baseUrl() {
    return apiBuild.baseUrl();
  }

  @Override public OkHttpClient httpClient() {
    return apiBuild.httpClient();
  }

  @Override public ReqType reqType() {
    return ReqType.GET;
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
    return JSON.parseObject(result.data(), clz);
  }

  /*********************************/

  //接口名
  public abstract String apiName();
}
