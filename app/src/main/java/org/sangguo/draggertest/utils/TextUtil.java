package org.sangguo.draggertest.utils;

import android.text.TextUtils;

/**
 * Created by chenwei on 2017/6/7.
 */

public class TextUtil {

  public static boolean isEmpty(String string) {
    return TextUtils.isEmpty(string) || TextUtils.isEmpty(string.trim());
  }
}
