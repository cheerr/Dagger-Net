package org.sangguo.draggertest.module.abs.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import java.lang.reflect.Field;
import org.sangguo.draggertest.http.interfaces.LifeInterface;
import org.sangguo.draggertest.observer.ObserverFactory;

/**
 * 提供底层支持
 * Created by chenwei on 2017/6/11.
 */

public abstract class AbstractFragment extends Fragment implements LifeInterface {

  private Unbinder unbinder;

  @Override public void onCreate(Bundle savedInstanceState) {
    beforeOnCreated(savedInstanceState);
    super.onCreate(savedInstanceState);
  }

  /**
   * 支持ButterKnife
   */
  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
    unbinder = null;
  }

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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onDestroy() {
    ObserverFactory.unregister(this); //统一销毁Activity下的Observer
    super.onDestroy();
  }

  /**
   * 模拟生命周期，OnCreated之前
   */
  protected void beforeOnCreated(Bundle savedInstanceState) {

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
