package org.sangguo.draggertest.observer;

import android.os.Looper;

/**
 * Created by chenwei on 2017/6/12.
 */
public interface ObserverInterface {

  void post(ObsData bundle);

  void post(ObsData bundle, Looper looper);

  void registerObserve(Object subscriber, ObsRunnable runnable);

  void unregisterObserve(Object subscriber);
}
