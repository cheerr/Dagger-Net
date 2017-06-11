package org.sangguo.draggertest.module.modules.home.inject;

import android.os.Bundle;
import org.sangguo.draggertest.dagger.HasComponent;
import org.sangguo.draggertest.module.abs.fragment.BaseBarFragment;
import org.sangguo.draggertest.module.modules.home.di.DaggerMainActivityComponent;
import org.sangguo.draggertest.module.modules.home.di.HomeMainComponent;
import org.sangguo.draggertest.module.modules.home.di.HomeMainModule;

/**
 * Created by chenwei on 2017/6/11.
 */

/**
 * 分业务注入，每块业务模块一个或多个InjectXXXFragment（取决于Component的数量）
 *
 * 本身没实现什么业务逻辑，只是方便子类继承使用
 */
public abstract class InjectHomeMainBarFragment extends BaseBarFragment implements
    HasComponent<HomeMainComponent> {

  private HomeMainComponent component;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeInjector();
  }

  private void initializeInjector() {
    this.component =
        DaggerMainActivityComponent.builder().applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .mainActivityModule(new HomeMainModule())
            .build();
    this.inject(component);
  }

  @Override public HomeMainComponent getComponent() {
    return component;
  }
}
