package org.sangguo.draggertest.module.abs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import org.sangguo.draggertest.module.abs.presenter.Presenter;
import org.sangguo.draggertest.module.abs.presenter.PresenterInjector;
import org.sangguo.draggertest.module.abs.presenter.PresenterSetter;

/**
 * 赋予Presenter，Fragment的生命周期
 * Created by chenwei on 2017/6/11.
 */

public class AbstractPresenterLifeCycleFragment extends AbstractFragment
    implements PresenterSetter {

  private Presenter presenter;

  /**
   * 提供手动设置Presenter的方法
   */
  @Override
  public void setPresenter(Presenter presenter) {
    if (this.presenter == null) {
      this.presenter = presenter;
    }
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    PresenterInjector.injectPresenter(this);
    if (presenter != null) {
      presenter.createdView();
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    if (presenter != null) {
      presenter.start();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (presenter != null) {
      presenter.resume();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if (presenter != null) {
      presenter.pause();
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    if (presenter != null) {
      presenter.stop();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.destroy();
      presenter = null;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (presenter != null) {
      presenter.onActivityResult(requestCode, resultCode, data);
    }
  }
}
