package org.sangguo.draggertest.module.abs.activity;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.sangguo.draggertest.module.abs.presenter.Presenter;
import org.sangguo.draggertest.module.abs.presenter.PresenterInjector;
import org.sangguo.draggertest.module.abs.presenter.PresenterSetter;

/**
 * 赋予Presenter，Activity的生命周期
 * Created by chenwei on 2017/6/11.
 */

public abstract class AbstractPresenterLifeCycleActivity extends AbstractFragmentActivity
    implements PresenterSetter {

  private List<Presenter> presenters = new ArrayList<>();//注意各个生命周期函数不要太耗时，不然可能有并发问题，待观察

  private Comparator<Presenter> presenterComparator = new Comparator<Presenter>() {
    @Override public int compare(Presenter o1, Presenter o2) {
      return o2.priority() - o1.priority();
    }
  };

  /**
   * 在setContentView方法之前调用，不然一些方法可能无法执行
   * 推荐都利用@PresenterLifeCycle注解
   * 提供手动设置Presenter的方法
   */
  @Override
  public void addPresenter(Presenter presenter) {
    if (presenter != null && !presenters.contains(presenter)) {
      presenters.add(presenter);
      Collections.sort(presenters, presenterComparator);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).onNewIntent(intent);
    }
  }

  @Override
  protected void onViewCreated() {
    PresenterInjector.injectPresenter(this);
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).createdView();
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).start();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).resume();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).pause();
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).stop();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).destroy();
    }
    presenters.clear();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).onActivityResult(requestCode, resultCode, data);
    }
  }
}
