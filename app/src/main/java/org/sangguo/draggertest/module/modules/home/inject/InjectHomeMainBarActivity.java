package org.sangguo.draggertest.module.modules.home.inject;

import android.os.Bundle;
import org.sangguo.draggertest.dagger.ComponentGetter;
import org.sangguo.draggertest.dagger.ComponentInject;
import org.sangguo.draggertest.module.abs.activity.BaseBarActivity;
import org.sangguo.draggertest.module.modules.home.di.DaggerHomeMainComponent;
import org.sangguo.draggertest.module.modules.home.di.HomeMainComponent;
import org.sangguo.draggertest.module.modules.home.di.HomeMainModule;

/**
 * Created by chenwei on 2017/6/7.
 */

/**
 * 分业务注入，每块业务模块一个或多个InjectXXXActivity（取决于Component的数量）
 *
 * 本身没实现什么业务逻辑，只是方便子类继承使用
 */
public abstract class InjectHomeMainBarActivity extends BaseBarActivity
    implements ComponentGetter<HomeMainComponent>, ComponentInject<HomeMainComponent> {

  private HomeMainComponent component;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeInjector();
  }

  private void initializeInjector() {
    this.component =
        DaggerHomeMainComponent.builder().applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .homeMainModule(new HomeMainModule())
            .build();
    this.inject(component);
  }

  @Override public HomeMainComponent getComponent() {
    return component;
  }
}
