package org.sangguo.draggertest.http.core;

import org.json.JSONObject;
import org.sangguo.draggertest.http.cache.ApiCache;
import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.callback.ResponseListener;
import org.sangguo.draggertest.http.core.build.ApiBuild;
import org.sangguo.draggertest.http.params.Params;
import org.sangguo.draggertest.http.util.ResponseInterfaceNull;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by chenwei on 2017/6/9.
 */

public class ApiExecuteImpl implements ApiExecute {

  private Class<? extends ApiInterface> apiClass;
  private ApiBuild apiBuild;
  private ApiInterface api;

  public ApiExecuteImpl(Class<? extends ApiInterface> apiClass) {
    this.apiClass = apiClass;
    this.apiBuild = null;
  }

  public ApiExecuteImpl(Class<? extends ApiInterface> apiClass, ApiBuild apiBuild) {
    this.apiClass = apiClass;
    this.apiBuild = apiBuild;
  }

  /**
   * 由于枚举变量程序启动时生成，这边不直接生成ApiInterface，调用时生成
   */
  private ApiInterface prepare() {
    if (api == null) {
      if (apiBuild != null) {
        api = ApiCache.newInstance().getApi(apiClass, apiBuild);
      } else {
        api = ApiCache.newInstance().getApi(apiClass);
      }
    }
    return api;
  }

  @Override public void execute(Params params, ResponseInterface handler) {
    this.prepare();
    api.request(params, ResponseInterfaceNull.avoidNull(handler));
  }

  @Override public <T> Observable<T> execute(final Params params, final Class<T> beanClass) {
    this.prepare();
    return Observable.create(new Observable.OnSubscribe<T>() {
      @Override public void call(final Subscriber<? super T> subscriber) {
        api.request(params, new ResponseListener() {

          @Override public void onResponseSuccess(JSONObject response) {
            subscriber.onNext(api.dataParse(response, beanClass));
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
