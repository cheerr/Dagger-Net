package org.sangguo.draggertest.observer;

import android.os.Looper;
import android.util.Log;

/**
 * ObserverInterface的包装类
 * Created by chenwei on 2017/6/12.
 */
public abstract class ObserverImplWrapper implements ObserverInterface {

  private ObserverInterface observeInterface;

  public ObserverImplWrapper() {
    this.observeInterface = ObserverFactory.newInstance(getObserverName());
    Log.i("Observer", "name:" + getObserverName());
  }

  protected String getObserverName() {
    return getClass().getName();
  }

  @Override
  public void post(ObsData obsData) {
    observeInterface.post(obsData);
  }

  @Override public void post(ObsData obsData, Looper looper) {
    observeInterface.post(obsData, looper);
  }

  @Override public void registerObserve(Object subscriber, ObsRunnable runnable) {
    observeInterface.registerObserve(subscriber, runnable);
  }

  @Override public void unregisterObserve(Object subscriber) {
    observeInterface.unregisterObserve(subscriber);
  }
}
