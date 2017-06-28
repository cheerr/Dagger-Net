package org.sangguo.draggertest.module.abs.presenter;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;

/**
 * 简化Presenter中对View的获取
 * Created by chenwei on 2017/6/11.
 */

public abstract class BaseViewPresenter<T> extends BasePresenter<T> {

  private View decorView;

  protected View findViewById(@IdRes int id) {
    return decorView == null ? null : decorView.findViewById(id);
  }

  public View getDecorView() {
    return decorView;
  }

  /**
   * 支持ButterKnife
   */
  @Override
  public void createdView(View view) {
    this.decorView = view;
    ButterKnife.bind(this, view);
  }

  /************简化布局里View的获取*************/
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
