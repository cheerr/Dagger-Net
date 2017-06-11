package org.sangguo.draggertest.module.abs.activity;

import android.content.Intent;
import android.os.Bundle;
import org.sangguo.draggertest.module.abs.presenter.Presenter;
import org.sangguo.draggertest.module.abs.presenter.PresenterInjector;
import org.sangguo.draggertest.module.abs.presenter.PresenterSetter;

/**
 * 赋予Presenter，Activity的生命周期
 * Created by chenwei on 2017/6/11.
 */

public abstract class AbstractPresenterLifeCycleActivity extends AbstractFragmentActivity
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
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    if (presenter != null) {
      presenter.onNewIntent(intent);
    }
  }

  @Override
  protected void onViewCreated() {
    PresenterInjector.injectPresenter(this);
    if (presenter != null) {
      presenter.createdView();
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (presenter != null) {
      presenter.start();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (presenter != null) {
      presenter.resume();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (presenter != null) {
      presenter.pause();
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (presenter != null) {
      presenter.stop();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.destroy();
      presenter = null;
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (presenter != null) {
      presenter.onActivityResult(requestCode, resultCode, data);
    }
  }
}
