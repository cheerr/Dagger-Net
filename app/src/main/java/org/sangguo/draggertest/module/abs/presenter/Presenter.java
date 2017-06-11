package org.sangguo.draggertest.module.abs.presenter;

import android.content.Intent;

public interface Presenter {

  int priority(); //优先级，用于排序

  void createdView();

  void start();

  void resume();

  void pause();

  void stop();

  void destroy();

  void onNewIntent(Intent intent);

  void onActivityResult(int requestCode, int resultCode, Intent data);
}