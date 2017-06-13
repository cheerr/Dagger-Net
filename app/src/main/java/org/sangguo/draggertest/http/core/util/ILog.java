package org.sangguo.draggertest.http.core.util;

import android.util.Log;

/**
 * Created by chenwei on 2017/6/13.
 */

public class ILog {

  private static boolean DEBUG = true;

  public static void setDebug(boolean debug) {
    ILog.DEBUG = debug;
  }

  public static void i(String tag, String msg) {
    if (DEBUG) {
      Log.i(tag, msg);
    }
  }

  public static void e(String tag, String msg) {
    if (DEBUG) {
      Log.e(tag, msg);
    }
  }

  public static void d(String tag, String msg) {
    if (DEBUG) {
      Log.d(tag, msg);
    }
  }
}
