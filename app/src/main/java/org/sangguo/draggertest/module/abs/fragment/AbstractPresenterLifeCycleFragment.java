package org.sangguo.draggertest.module.abs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.sangguo.draggertest.module.abs.presenter.Presenter;
import org.sangguo.draggertest.module.abs.presenter.PresenterInjector;
import org.sangguo.draggertest.module.abs.presenter.PresenterSetter;

/**
 * 赋予Presenter，Fragment的生命周期
 * Created by chenwei on 2017/6/11.
 */

public class AbstractPresenterLifeCycleFragment extends AbstractFragment
    implements PresenterSetter {

  private List<Presenter> presenters = new ArrayList<>();//注意各个生命周期函数不要太耗时，不然可能有并发问题，待观察

  private Comparator<Presenter> presenterComparator = new Comparator<Presenter>() {
    @Override public int compare(Presenter o1, Presenter o2) {
      return o2.priority() - o1.priority();
    }
  };

  /**
   * 在onViewCreated方法之前调用，不然一些方法可能无法执行
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
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    PresenterInjector.injectPresenter(this);
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).createdView();
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).start();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).resume();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).pause();
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).stop();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).destroy();
    }
    presenters.clear();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    for (int i = 0; i < presenters.size(); i++) {
      presenters.get(i).onActivityResult(requestCode, resultCode, data);
    }
  }
}
