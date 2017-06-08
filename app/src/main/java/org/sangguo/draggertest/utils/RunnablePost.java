package org.sangguo.draggertest.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

/**
 * Created by chenwei on 2017/6/8.
 */

public class RunnablePost {

  public static void post(@NonNull Runnable runnable) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      runnable.run();
    } else {
      new Handler(Looper.getMainLooper()).post(runnable);
    }
  }

  public static void postOn(@NonNull Runnable runnable, Looper looper) {
    if (looper == null || Looper.myLooper() == looper) {
      runnable.run();
    } else {
      new Handler(looper).post(runnable);
    }
  }
}
