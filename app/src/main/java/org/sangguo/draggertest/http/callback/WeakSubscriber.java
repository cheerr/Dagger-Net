package org.sangguo.draggertest.http.callback;

import android.app.Activity;
import android.support.v4.app.Fragment;
import java.lang.ref.WeakReference;
import rx.Subscriber;

/**
 * Created by chenwei on 2017/6/8.
 */

public abstract class WeakSubscriber<T> extends Subscriber<T> {

  private WeakReference<Object> weakReference;

  public WeakSubscriber(Object object) {
    if (object != null) {
      weakReference = new WeakReference<Object>(object);
    }
  }

  /**
   * 判断对象是否销毁
   */
  private boolean isShouldDealResponse() {
    boolean isExist = weakReference != null && weakReference.get() != null;
    if (isExist) {
      Object object = weakReference.get();
      if (object instanceof Activity) {
        isExist = !((Activity) object).isFinishing();
      }

      if (object instanceof Fragment) {
        isExist = !((Fragment) object).isDetached();
      }
    }
    return isExist;
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
