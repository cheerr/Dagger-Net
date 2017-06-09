package org.sangguo.draggertest.http;

import org.sangguo.draggertest.http.apis.TestApi;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.core.ApiExecute;
import org.sangguo.draggertest.http.core.ApiExecuteImpl;
import org.sangguo.draggertest.http.core.ApiInterface;
import org.sangguo.draggertest.http.core.build.ApiBuild;
import org.sangguo.draggertest.http.params.Params;
import rx.Observable;

/**
 * Created by chenwei on 2017/6/7.
 */

public enum ApiEnum {

  TEST1(TestApi.class),
  TEST2(TestApi.class),
  TEST3(TestApi.class),
  TEST4(TestApi.class),
  TEST5(TestApi.class),
  TEST6(TestApi.class),
  TEST7(TestApi.class),
  TEST8(TestApi.class);

  private ApiExecute apiExecute;

  private ApiEnum(Class<? extends ApiInterface> apiClass) {
    apiExecute = new ApiExecuteImpl(apiClass);
  }

  private ApiEnum(Class<? extends ApiInterface> apiClass, ApiBuild apiBuild) {
    apiExecute = new ApiExecuteImpl(apiClass, apiBuild);
  }

  /**
   * 回调方式请求数据，结果在ResponseInterface里面
   */
  public void execute(Params params, ResponseInterface handler) {
    apiExecute.execute(params, handler);
  }

  /**
   * 提供RxJava回调方式，注意subscribeOn不能设为主线程
   */
  public <T> Observable<T> execute(Params params, Class<T> beanClass) {
    return apiExecute.execute(params, beanClass);
  }
}
