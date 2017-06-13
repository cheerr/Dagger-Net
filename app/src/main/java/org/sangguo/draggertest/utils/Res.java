package org.sangguo.draggertest.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

/**
 * Created by chenwei on 2017/6/7.
 */

public class Res {

  private static Context appContext;

  public static void initRes(Context context) {
    Res.appContext = context.getApplicationContext();
  }

  public static String string(@StringRes int id) {
    return Res.appContext.getString(id);
  }

  public static int color(@ColorRes int id) {
    return ContextCompat.getColor(Res.appContext, id);
  }

  public static Drawable drawable(@DrawableRes int id) {
    return ContextCompat.getDrawable(Res.appContext, id);
  }

  public static float dimen(@DimenRes int id) {
    return Res.appContext.getResources().getDimension(id);
  }

  public static float dimensionPixelSize(@DimenRes int id) {
    return Res.appContext.getResources().getDimensionPixelSize(id);
  }
}
