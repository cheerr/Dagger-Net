package org.sangguo.draggertest.module.abs.activity;

import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import org.sangguo.draggertest.http.interfaces.LifeInterface;

/**
 * 提供底层支持
 * Created by chenwei on 2017/6/11.
 */

public abstract class AbstractFragmentActivity extends FragmentActivity implements LifeInterface {

  @Override
  public void setContentView(@LayoutRes int id) {
    super.setContentView(id);
    handleViewCreated(getWindow().getDecorView());
  }

  @Override
  public void setContentView(View view) {
    super.setContentView(view);
    handleViewCreated(view);
  }

  @Override
  public void setContentView(View view, ViewGroup.LayoutParams params) {
    super.setContentView(view, params);
    handleViewCreated(view);
  }

  private boolean viewCreated = false;

  /**
   * 支持ButterKnife
   */
  private void handleViewCreated(View view) {
    ButterKnife.bind(this, view);
    if (!viewCreated) {
      onViewCreated(view);
      viewCreated = true;
    }
  }

  /**
   * 模拟生命周期，View创建之后，只会调用一次
   */
  protected abstract void onViewCreated(View view);

  /**
   * 尽量提供和Fragment一致的方法
   */
  public FragmentActivity getActivity() {
    return this;
  }

  @Override public boolean isDestroy() {
    return isFinishing();
  }
}
