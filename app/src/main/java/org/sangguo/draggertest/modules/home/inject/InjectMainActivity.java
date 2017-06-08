package org.sangguo.draggertest.modules.home.inject;

import android.os.Bundle;
import org.sangguo.draggertest.internal.HasComponent;
import org.sangguo.draggertest.modules.abs.BasePresenterWrapperActivity;
import org.sangguo.draggertest.modules.home.di.DaggerMainActivityComponent;
import org.sangguo.draggertest.modules.home.di.MainActivityComponent;
import org.sangguo.draggertest.modules.home.di.MainActivityModule;

/**
 * Created by chenwei on 2017/6/7.
 */

/**
 * 分业务注入，每块业务模块一个或多个InjectXXXActivity（取决于Component的数量）
 */
public abstract class InjectMainActivity extends BasePresenterWrapperActivity
    implements HasComponent<MainActivityComponent> {

  private MainActivityComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeInjector();
  }

  private void initializeInjector() {
    this.component =
        DaggerMainActivityComponent.builder().applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .mainActivityModule(new MainActivityModule())
            .build();
    this.inject(component);
  }

  @Override public MainActivityComponent getComponent() {
    return component;
  }
}