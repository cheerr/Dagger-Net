package org.sangguo.draggertest.module.abs.fragment;

import android.support.v4.app.Fragment;
import org.sangguo.draggertest.http.interfaces.LifeInterface;

/**
 * 提供底层接口支持
 * Created by chenwei on 2017/6/11.
 */

public abstract class AbstractFragment extends Fragment implements LifeInterface {

  @Override public boolean isDestroy() {
    return isDetached() || getView() == null;
  }
}
