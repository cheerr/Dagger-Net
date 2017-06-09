package org.sangguo.draggertest.http.cache;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.sangguo.draggertest.http.core.ApiInterface;

/**
 * Created by chenwei on 2017/6/8.
 */

public class ApiCache {

  private static ApiCache instance;

  private Map<String, ApiInterface> cacheMap;

  private ApiCache() {
    cacheMap = new HashMap<>();
  }

  public static synchronized ApiCache newInstance() {
    if (instance == null) {
      synchronized (ApiCache.class) {
        if (instance == null) {
          instance = new ApiCache();
        }
      }
    }
    return instance;
  }

  /**
   * 获取ApiInterface实例变量
   */
  public ApiInterface getApi(Class<? extends ApiInterface> apiClass) {
    if (cacheMap.get(apiClass) != null) {
      return cacheMap.get(apiClass);
    }
    Log.i("http", "apiClass:" + apiClass.getName());
    ApiInterface apiInterface = reflect(apiClass);
    if (apiInterface != null && apiInterface.cache()) {
      cacheMap.put(apiClass.getName(), apiInterface);
    }
    return apiInterface;
  }

  /**
   * 反射得到ApiInterface实例
   */
  private ApiInterface reflect(Class<? extends ApiInterface> apiClass) {
    ApiInterface apiInterface = null;
    try {
      apiInterface = apiClass.newInstance();
      return apiInterface;
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return null;
  }
}
