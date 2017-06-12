package org.sangguo.draggertest.observer.example;

import org.sangguo.draggertest.observer.ObserverImplWrapper;

/**
 * Created by chenwei on 2017/6/12.
 */

public class UserUpdateObserver extends ObserverImplWrapper {

  private static UserUpdateObserver instance;

  public synchronized static UserUpdateObserver newInstance() {
    if (instance == null) {
      instance = new UserUpdateObserver();
    }
    return instance;
  }
}
