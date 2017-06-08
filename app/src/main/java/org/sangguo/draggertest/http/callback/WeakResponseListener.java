package org.sangguo.draggertest.http.callback;

import android.app.Activity;
import android.app.Fragment;
import java.lang.ref.WeakReference;

/**
 * 主要应用于Activity或者Fragment销毁时，请求返回的情况
 * Created by chenwei on 2017/6/8.
 */
public class WeakResponseListener extends ResponseListener {

  private WeakReference<Object> weakReference;

  public WeakResponseListener(Object object) {
    if (object != null) {
      weakReference = new WeakReference<>(object);
    }
  }

  @Override
  protected boolean isShouldDealResponse() {
    boolean isExist = weakReference != null && weakReference.get() != null;
    if (isExist) {
      Object object = weakReference.get();
      if (object instanceof Activity) {
        isExist = !((Activity) object).isFinishing();
      }

      if (object instanceof Fragment) {
        isExist = !((Fragment) object).isDetached();
      }
    }
    return isExist;
  }
}
