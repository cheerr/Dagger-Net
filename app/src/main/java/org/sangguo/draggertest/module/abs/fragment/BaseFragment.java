package org.sangguo.draggertest.module.abs.fragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.sangguo.draggertest.TestApplication;
import org.sangguo.draggertest.dagger.di.components.ApplicationComponent;
import org.sangguo.draggertest.dagger.di.modules.ActivityModule;
import org.sangguo.draggertest.module.abs.activity.BaseActivity;

/**
 * Created by chenwei on 2017/6/11.
 */

public class BaseFragment extends AbstractPresenterLifeCycleFragment {

  private int layout_id;

  /**
   * 采用和Activity一致的方式装载布局
   */
  public void setContentView(int layout_id) {
    this.layout_id = layout_id;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view;
    if ((view = getView()) == null && layout_id != 0) {
      view = inflater.inflate(layout_id, container, false);
    }
    return view;
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((TestApplication) getActivity().getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule((BaseActivity) getActivity());
  }

  @Override
  public void onDestroy() {
    if (getActivity() != null && ((BaseActivity) getActivity()).getCurrFragment() == this) {
      ((BaseActivity) getActivity()).setCurrFragment(null);
    }
    super.onDestroy();
  }

  /**
   * 在BaseActivity里设置setCurrFragment有效
   */
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    return false;
  }

  /************Activity常用的几个方法*************/

  public final View findViewById(int id) {
    if (getView() == null) return null;
    return getView().findViewById(id);
  }

  public Application getApplication() {
    if (getActivity() != null) {
      return getActivity().getApplication();
    }
    return null;
  }

  public void runOnUiThread(Runnable runnable) {
    if (runnable != null && !isDestroy()) {
      getActivity().runOnUiThread(runnable);
    }
  }

  /************简化布局里View的获取，底层支持ButterKnife，取决于个人习惯*************/

  //简化布局里View的获取
  public <T extends View> T bindView(@IdRes int resId) {
    return (T) findViewById(resId);
  }

  public <T extends View> T bindView(View view, @IdRes int resId) {
    if (view == null) return null;
    return (T) view.findViewById(resId);
  }

  public <T extends View> T bindView(@IdRes int resId, View.OnClickListener listener) {
    T v = (T) findViewById(resId);
    if (v != null) {
      v.setOnClickListener(listener);
    }
    return v;
  }

  public <T extends View> T bindView(View view, @IdRes int resId, View.OnClickListener listener) {
    if (view == null) return null;
    T v = (T) view.findViewById(resId);
    if (v != null) {
      v.setOnClickListener(listener);
    }
    return v;
  }

  public <T extends View> T bindView(@IdRes int resId, boolean visible) {
    T v = (T) findViewById(resId);
    if (v != null) {
      v.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    return v;
  }

  public <T extends View> T bindText(@IdRes int resId, String text) {
    T v = (T) findViewById(resId);
    if (v != null && v instanceof TextView) {
      ((TextView) v).setText(text);
    }
    return v;
  }
}
