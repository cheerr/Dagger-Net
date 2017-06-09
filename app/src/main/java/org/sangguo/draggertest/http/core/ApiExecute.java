package org.sangguo.draggertest.http.core;

import org.sangguo.draggertest.http.callback.ResponseInterface;
import org.sangguo.draggertest.http.params.Params;
import rx.Observable;

/**
 * Created by chenwei on 2017/6/9.
 */

public interface ApiExecute {

  void execute(Params params, ResponseInterface handler);

  <T> Observable<T> execute(final Params params, final Class<T> beanClass);
}
