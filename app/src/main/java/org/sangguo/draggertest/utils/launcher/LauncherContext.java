package org.sangguo.draggertest.utils.launcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import org.sangguo.draggertest.config.App;

/**
 * Created by chenwei on 2017/6/7.
 */

public class LauncherContext {

  private Object context;

  public LauncherContext(Context context) {
    this.context = context;
  }

  public LauncherContext(Fragment context) {
    this.context = context;
  }

  public void startActivity(Intent intent) {
    if (context instanceof Activity) {
      ((Activity) context).startActivity(intent);
    } else if (context instanceof Fragment) {
      ((Fragment) context).startActivity(intent);
    } else {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      App.appContext.startActivity(intent);
    }
  }

  public void startActivityForResult(Intent intent, int forResult) {
    if (context instanceof Activity) {
      ((Activity) context).startActivityForResult(intent, forResult);
    } else if (context instanceof Fragment) {
      ((Fragment) context).startActivityForResult(intent, forResult);
    } else {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      App.appContext.startActivity(intent);
    }
  }

  public Context getContext() {
    if (context instanceof Context) {
      return (Context) context;
    } else if (context instanceof Fragment && ((Fragment) context).getActivity() != null) {
      return ((Fragment) context).getActivity();
    } else {
      return App.appContext;
    }
  }
}
