package org.sangguo.draggertest.module.abs.presenter;

import android.content.Intent;

/**
 * Created by chenwei on 2017/6/11.
 */

public abstract class BasePresenter<T> implements Presenter {

  protected T viewer;

  public T getViewer() {
    return viewer;
  }

  public void setViewer(T viewer) {
    this.viewer = viewer;
  }

  @Override public int priority() {
    return 0;
  }

  @Override public void start() {

  }

  @Override public void stop() {

  }

  @Override public void newIntent(Intent intent) {

  }

  @Override public void activityResult(int requestCode, int resultCode, Intent data) {

  }
}
