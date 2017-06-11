package org.sangguo.draggertest.http.callback;

import java.lang.ref.WeakReference;
import org.sangguo.draggertest.http.interfaces.LifeInterface;
import rx.Subscriber;

/**
 * Created by chenwei on 2017/6/8.
 */

public abstract class LifeSubscriber<T> extends Subscriber<T> {

  private WeakReference<LifeInterface> weakReference;

  public LifeSubscriber(LifeInterface lifeInterface) {
    if (lifeInterface != null) {
      weakReference = new WeakReference<>(lifeInterface);
    }
  }

  /**
   * 判断对象是否销毁
   */
  private boolean isShouldDealResponse() {
    return weakReference != null && weakReference.get() != null && !weakReference.get().isDestroy();
  }

  public abstract void onDataSuccess(T t);

  public abstract void onDataFailure();

  public abstract void onDataCompleted();

  @Override public final void onCompleted() {
    if (isShouldDealResponse()) {
      onDataCompleted();
    }
  }

  //调用onError的时候不会调用onCompleted和onNext
  @Override public final void onError(Throwable e) {
    onNext(null);
    onCompleted();
  }

  @Override public final void onNext(T t) {
    if (isShouldDealResponse()) {
      if (t != null) {
        onDataSuccess(t);
      } else {
        onDataFailure();
      }
    }
  }
}
