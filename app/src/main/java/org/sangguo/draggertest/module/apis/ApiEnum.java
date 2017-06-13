package org.sangguo.draggertest.module.apis;

import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.core.ApiExecute;
import org.sangguo.draggertest.http.core.ApiExecuteImpl;
import org.sangguo.draggertest.http.core.ApiInterface;
import org.sangguo.draggertest.http.params.Params;
import rx.Observable;

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
