package org.sangguo.draggertest.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import org.sangguo.draggertest.config.App;

/**
 * Created by chenwei on 2017/6/7.
 */

public class Res {

  public static String string(@StringRes int id) {
    return App.appContext.getString(id);
  }

  public static int color(@ColorRes int id) {
    return ContextCompat.getColor(App.appContext, id);
  }

  public static Drawable drawable(@DrawableRes int id) {
    return ContextCompat.getDrawable(App.appContext, id);
  }

  public static float dimen(@DimenRes int id) {
    return App.appContext.getResources().getDimension(id);
  }

  public static float dimensionPixelSize(@DimenRes int id) {
    return App.appContext.getResources().getDimensionPixelSize(id);
  }
}
