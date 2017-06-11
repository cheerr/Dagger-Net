package org.sangguo.draggertest.http.callback;

import java.lang.ref.WeakReference;
import org.sangguo.draggertest.http.interfaces.LifeInterface;

/**
 * 主要应用于Activity或者Fragment销毁时，请求返回的情况
 * Created by chenwei on 2017/6/8.
 */
public class LifeResponseListener extends ResponseListener {

  private WeakReference<LifeInterface> weakReference;

  public LifeResponseListener(LifeInterface lifeInterface) {
    if (lifeInterface != null) {
      weakReference = new WeakReference<>(lifeInterface);
    }
  }

  @Override
  protected boolean isShouldDealResponse() {
    return weakReference != null && weakReference.get() != null && !weakReference.get().isDestroy();
  }
}
