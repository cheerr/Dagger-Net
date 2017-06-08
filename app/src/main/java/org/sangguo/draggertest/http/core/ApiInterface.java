package org.sangguo.draggertest.http.core;

import okhttp3.OkHttpClient;
import org.json.JSONObject;
import org.sangguo.draggertest.http.core.type.ReqType;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.core.util.RequestBuilderDo;
import org.sangguo.draggertest.http.params.Params;

/**
 * Created by chenwei on 2017/6/8.
 */

public interface ApiInterface {

  public String baseUrl();

  public String apiName();

  public OkHttpClient httpClient();

  public ReqType reqType();//请求类型 get or post

  public boolean cache();//是否缓存

  public void encryptParams(Params params); //加密或者修改 参数

  public RequestBuilderDo requestBuilderDo();//处理RequestBuilder的类，比如添加Header之类都在这边做

  public void request(Params params, ResponseInterface responseInterface);

  public <T> T dataParse(JSONObject json, Class<T> clz); //数据解析
}
