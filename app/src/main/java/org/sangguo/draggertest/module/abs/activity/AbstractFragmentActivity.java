package org.sangguo.draggertest.module.abs.activity;

import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * 提供底层接口支持
 * Created by chenwei on 2017/6/11.
 */

public abstract class AbstractFragmentActivity extends FragmentActivity {

  @Override
  public void setContentView(@LayoutRes int id) {
    super.setContentView(id);
    onViewCreated();
  }

  @Override
  public void setContentView(View view) {
    super.setContentView(view);
    onViewCreated();
  }

  @Override
  public void setContentView(View view, ViewGroup.LayoutParams params) {
    super.setContentView(view, params);
    onViewCreated();
  }

  /**
   * 模拟生命周期，View创建之后
   */
  protected abstract void onViewCreated();

  /**
   * 尽量提供和Fragment一致的方法
   */
  public FragmentActivity getActivity() {
    return this;
  }
}
