package org.sangguo.draggertest;

import android.app.Application;
import org.sangguo.draggertest.config.App;
import org.sangguo.draggertest.internal.di.components.ApplicationComponent;
import org.sangguo.draggertest.internal.di.components.DaggerApplicationComponent;
import org.sangguo.draggertest.internal.di.modules.ApplicationModule;

/**
 * Created by chenwei on 2017/6/7.
 */

public class TestApplication extends Application {

  private ApplicationComponent applicationComponent;

  private TestLifeCycleCallbacks testLifeCycleCallbacks = new TestLifeCycleCallbacks();

  @Override public void onCreate() {
    super.onCreate();
    this.initializeInjector();
    this.initializeApp();
  }

  /**
   * 初始化应用
   */
  private void initializeApp() {
    App.initApp(this);
    if (testLifeCycleCallbacks != null) {
      registerActivityLifecycleCallbacks(testLifeCycleCallbacks);
    }
  }

  private void initializeInjector() {
    this.applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }

  /**
   * 退出应用
   */
  public void exitApp() {
    if (testLifeCycleCallbacks != null) {
      testLifeCycleCallbacks.exitApp();
    }
  }
}
