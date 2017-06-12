package org.sangguo.draggertest.observer;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ItemObserver的管理类，在Activity中统一管理
 * Created by chenwei on 2017/6/12.
 */
public final class ObserverFactory {

  private static Map<String, ObserverImpl> observeMap = new ConcurrentHashMap<>();

  public static ObserverImpl newInstance(@NonNull String name) {
    ObserverImpl itemObserve = observeMap.get(name);
    if (itemObserve == null) {
      itemObserve = new ObserverImpl(name);
      observeMap.put(name, itemObserve);
    }
    return itemObserve;
  }

  public static boolean hasObserver(@NonNull String name) {
    return observeMap.containsKey(name);
  }

  /**
   * 在Activity的onDestroy直接调用unregister（this）
   */
  public static void unregister(Object code) {
    for (Map.Entry<String, ObserverImpl> entry : observeMap.entrySet()) {
      if (entry.getValue() != null) {
        entry.getValue().unregisterObserve(code);
      }
    }
  }

  public static void removeObserver(String key) {
    if (observeMap != null && !TextUtils.isEmpty(key)) {
      observeMap.remove(key);
    }
  }

  public static void clear() {
    if (observeMap != null) {
      observeMap.clear();
    }
  }
}
