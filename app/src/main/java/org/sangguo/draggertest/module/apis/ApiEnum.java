package org.sangguo.draggertest.module.apis;

import rx.Observable;
import thirdparty.http.lib.callback.AbstractResponseInterface;
import thirdparty.http.lib.core.ApiExecute;
import thirdparty.http.lib.core.ApiExecuteImpl;
import thirdparty.http.lib.core.ApiInterface;
import thirdparty.http.lib.params.Params;

/**
 * Created by chenwei on 2017/6/7.
 */

public enum ApiEnum {

  TEST1(TestApi1.class),
  TEST2(TestApi2.class),
  TEST3(TestApi1.class);

  private ApiExecute apiExecute;

  private ApiEnum(Class<? extends ApiInterface> apiClass) {
    apiExecute = new ApiExecuteImpl(apiClass);
  }

  /**
   * 回调方式请求数据，结果在ResponseInterface里面
   */
  public void execute(Params params, AbstractResponseInterface handler) {
    apiExecute.execute(params, handler);
  }

  /**
   * 提供RxJava回调方式，注意subscribeOn不能设为主线程
   */
  public <T> Observable<T> execute(Params params, Class<T> beanClass) {
    return apiExecute.execute(params, beanClass);
  }
}
