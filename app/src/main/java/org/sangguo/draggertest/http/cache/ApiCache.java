package org.sangguo.draggertest.http.cache;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import org.sangguo.draggertest.http.core.ApiInterface;
import org.sangguo.draggertest.http.core.build.ApiBuild;

/**
 * Created by chenwei on 2017/6/8.
 */

public class ApiCache {

  private static ApiCache instance;

  private Map<Class, ApiInterface> cacheMap;

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
    ApiInterface apiInterface = reflect(apiClass);
    if (apiInterface != null && apiInterface.cache()) {
      cacheMap.put(apiClass, apiInterface);
    }
    return apiInterface;
  }

  /**
   * 反射得到ApiInterface实例
   */
  private ApiInterface reflect(Class<? extends ApiInterface> apiClass) {
    ApiInterface apiInterface = null;
    try {
      Constructor e = apiClass.getConstructor();
      if (e != null) {
        apiInterface = (ApiInterface) e.newInstance();
      }
      return apiInterface;
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return null;
  }

  /**
   * 获取ApiInterface实例变量
   */
  public ApiInterface getApi(Class<? extends ApiInterface> apiClass, ApiBuild apiBuild) {
    if (cacheMap.get(apiClass) != null) {
      return cacheMap.get(apiClass);
    }
    ApiInterface apiInterface = reflect(apiClass, apiBuild);
    if (apiInterface != null && apiInterface.cache()) {
      cacheMap.put(apiClass, apiInterface);
    }
    return apiInterface;
  }

  /**
   * 反射得到ApiInterface实例
   */
  private ApiInterface reflect(Class<? extends ApiInterface> apiClass, ApiBuild apiBuild) {
    ApiInterface apiInterface = null;
    try {
      Constructor e = apiClass.getConstructor(ApiBuild.class);
      if (e != null) {
        apiInterface = (ApiInterface) e.newInstance(apiBuild);
      }
      return apiInterface;
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return null;
  }
}
