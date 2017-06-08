package org.sangguo.draggertest.utils.launcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.List;
import org.sangguo.draggertest.config.App;

/**
 * Created by chenwei on 2017/6/7.
 */

public class LauncherHelper {

  public static final String TAG = LauncherHelper.class.getSimpleName();

  private LauncherContext context;

  private List<Integer> flags = new ArrayList<>();

  private LauncherHelper(LauncherContext launcherContext) {
    this.context = launcherContext;
  }

  public static LauncherHelper from(Context context) {
    return new LauncherHelper(new LauncherContext(context));
  }

  public static LauncherHelper from(Fragment fragment) {
    return new LauncherHelper(new LauncherContext(fragment));
  }

  public static LauncherHelper fromApp() {
    return new LauncherHelper(new LauncherContext(App.appContext));
  }

  /**
   * 仅对Activity有效
   */
  public LauncherHelper addFlag(int flag) {
    if (!flags.contains(flag)) {
      flags.add(flag);
    }
    return this;
  }

  public void startActivity(Class<? extends Activity> clazz) {
    startActivity(clazz, (Bundle) null);
  }

  public void startActivity(Class<? extends Activity> clazz, Bundle bundle) {
    Intent intent = new Intent(context.getContext(), clazz);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    if (flags != null && flags.size() > 0) {
      for (Integer flag : flags) {
        intent.addFlags(flag);
      }
    }
    context.startActivity(intent);
  }

  public void startActivityForResult(Class<? extends Activity> clazz, int forResult) {
    startActivityForResult(clazz, null, forResult);
  }

  public void startActivityForResult(Class<? extends Activity> clazz, Bundle bundle,
      int forResult) {
    Intent intent = new Intent(context.getContext(), clazz);
    if (bundle != null) {
      intent.putExtras(bundle);
    }

    if (flags != null && flags.size() > 0) {
      for (Integer flag : flags) {
        intent.addFlags(flag);
      }
    }
    context.startActivityForResult(intent, forResult);
  }
}
