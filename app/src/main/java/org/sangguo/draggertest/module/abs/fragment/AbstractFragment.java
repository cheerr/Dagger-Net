package org.sangguo.draggertest.module.abs.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import java.lang.reflect.Field;
import org.sangguo.draggertest.http.interfaces.LifeInterface;

/**
 * 提供底层支持
 * Created by chenwei on 2017/6/11.
 */

public abstract class AbstractFragment extends Fragment implements LifeInterface {

  @Override public boolean isDestroy() {
    return getActivity() == null || isDetached() || getView() == null;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    try {
      Field childFragmentManager = Fragment.class
          .getDeclaredField("mChildFragmentManager");
      if (childFragmentManager != null) {
        childFragmentManager.setAccessible(true);
        childFragmentManager.set(this, null);
      }
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  /***************Api支持**************/

  @TargetApi(Build.VERSION_CODES.M)
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    onAttachToContext(context);
  }

  /*
   * Deprecated on API 23
   * Use onAttachToContext instead
   */
  @SuppressWarnings("deprecation")
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      onAttachToContext(activity);
    }
  }

  /*
   * Called when the fragment attaches to the context
   */
  protected void onAttachToContext(Context context) {

  }
}
