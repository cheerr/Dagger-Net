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

  protected boolean isShouldDealResponse() {

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

  @Override public void onCompleted() {
    //todo 可能需要处理
  }

  @Override public void onError(Throwable e) {
    if (isShouldDealResponse()) {
      onDataFailure();
    }
  }

  @Override public void onNext(T t) {
    if (isShouldDealResponse()) {
      onDataSuccess(t);
    }
  }
}
