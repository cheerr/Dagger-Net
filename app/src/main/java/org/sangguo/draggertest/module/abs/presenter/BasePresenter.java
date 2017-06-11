package org.sangguo.draggertest.module.abs.presenter;

import android.content.Intent;

/**
 * Created by chenwei on 2017/6/11.
 */

public abstract class BasePresenter implements Presenter {

  @Override public int priority() {
    return 0;
  }

  @Override public void start() {

  }

  @Override public void stop() {

  }

  @Override public void onNewIntent(Intent intent) {

  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {

  }
}
