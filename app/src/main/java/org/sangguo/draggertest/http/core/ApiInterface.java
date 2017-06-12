package org.sangguo.draggertest.http.core;

import okhttp3.OkHttpClient;
import org.json.JSONObject;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.core.configuration.ApiConfiguration;
import org.sangguo.draggertest.http.core.type.ReqType;
import org.sangguo.draggertest.http.core.util.RequestBuilderDeal;
import org.sangguo.draggertest.http.data.Result;
import org.sangguo.draggertest.http.params.Params;

/**
 * Created by chenwei on 2017/6/8.
 */

public interface ApiInterface {

  public ApiConfiguration getApiConfiguration(); //基础配置

  public String baseUrl();

  public String apiName();

  public OkHttpClient httpClient();

  public ReqType reqType();//请求类型 get or post

  public boolean cache();//是否缓存

  public void request(Params params, ResponseInterface responseInterface);

  public <T> T dataParse(JSONObject json, Class<T> clz); //数据解析
}
