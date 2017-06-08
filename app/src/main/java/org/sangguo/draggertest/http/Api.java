package org.sangguo.draggertest.http;

import org.json.JSONObject;
import org.sangguo.draggertest.http.core.ApiInterface;
import org.sangguo.draggertest.http.apis.TestApi;
import org.sangguo.draggertest.http.cache.ApiCache;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.callback.ResponseListener;
import org.sangguo.draggertest.http.params.Params;
import org.sangguo.draggertest.http.util.ResponseInterfaceNull;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by chenwei on 2017/6/7.
 */

public enum Api {

  TEST1(TestApi.class),
  TEST2(TestApi.class),
  TEST3(TestApi.class),
  TEST4(TestApi.class),
  TEST5(TestApi.class),
  TEST6(TestApi.class),
  TEST7(TestApi.class),
  TEST8(TestApi.class);

  private Class<? extends ApiInterface> apiClass;

  private Api(Class<? extends ApiInterface> clazz) {
    this.apiClass = clazz;
  }

  /**
   * 回调方式请求数据，结果在ResponseInterface里面
   */
  public void execute(Params params, ResponseInterface handler) {
    ApiInterface apiInterface = ApiCache.newInstance().getApi(apiClass);
    apiInterface.request(params, ResponseInterfaceNull.avoidNull(handler));
  }

  /**
   * 提供RxJava回调方式，注意subscribeOn不能设为主线程
   */
  public <T> Observable<T> execute(final Params params, final Class<T> beanClass) {
    return Observable.create(new Observable.OnSubscribe<T>() {
      @Override public void call(final Subscriber<? super T> subscriber) {
        final ApiInterface apiInterface = ApiCache.newInstance().getApi(apiClass);
        apiInterface.request(params, new ResponseListener() {

          @Override public void onResponseSuccess(JSONObject response) {
            subscriber.onNext(apiInterface.dataParse(response, beanClass));
            subscriber.onCompleted();
          }

          @Override public void onResponseFailure(int statusCode, String errorMsg) {
            subscriber.onNext(null);
            subscriber.onCompleted();
          }
        });
      }
    }).subscribeOn(Schedulers.io());
  }
}
