package org.sangguo.draggertest.observer;

import android.os.Looper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import thirdparty.http.lib.core.util.RunnablePost;

/**
 * Created by chenwei on 2017/6/12.
 */
public final class ObserverImpl implements ObserverInterface {

  private Map<Object, ObsRunnable> runnableMap = new ConcurrentHashMap<>();
  private String key;

  ObserverImpl(String key) {
    this.key = key;
  }

  /**
   * 提交到当前线程执行
   */
  @Override
  public void post(ObsData obsData) {
    post(obsData, Looper.myLooper());
  }

  /**
   * 提交到Looper上执行
   */
  @Override public void post(final ObsData obsData, Looper looper) {
    RunnablePost.postOn(new Runnable() {
      @Override public void run() {
        executeRunnable(obsData);
      }
    }, looper);
  }

  /**
   * 执行所有runnable
   */
  private void executeRunnable(ObsData obsData) {
    if (runnableMap.isEmpty()) {
      ObserverFactory.removeObserver(key);
      return;
    }
    for (Map.Entry<Object, ObsRunnable> entry : runnableMap.entrySet()) {
      ObsRunnable runnable = entry.getValue();
      if (runnable != null) {
        runnable.run(obsData);
      }
    }
  }

  @Override
  public void registerObserve(Object subscriber, ObsRunnable runnable) {
    runnableMap.put(subscriber, runnable);
  }

  @Override public void unregisterObserve(Object subscriber) {
    if (runnableMap.containsKey(subscriber)) {
      runnableMap.remove(subscriber);
    }
  }
}
