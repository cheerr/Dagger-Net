package org.sangguo.draggertest.modules.home.di;

import dagger.Module;
import dagger.Provides;
import org.sangguo.draggertest.internal.scope.PerActivity;
import org.sangguo.draggertest.modules.home.control.PageControl;
import org.sangguo.draggertest.modules.home.control.PageControlImpl;

/**
 * 按业务区分模块，这里存放home模块下的控制器
 * Created by chenwei on 2017/6/7.
 */

@Module
public class MainActivityModule {

  @Provides @PerActivity PageControl providePageControl(PageControlImpl impl) {
    return impl;
  }
}
