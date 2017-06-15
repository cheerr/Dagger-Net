package org.sangguo.draggertest.module.abs.presenter;

import android.content.Intent;
import android.view.View;

public interface Presenter {

  int priority(); //优先级，用于排序

  void createdView(View view);

  void start();

  void resume();

  void pause();

  void stop();

  void destroy();

  void newIntent(Intent intent);

  void activityResult(int requestCode, int resultCode, Intent data);
}