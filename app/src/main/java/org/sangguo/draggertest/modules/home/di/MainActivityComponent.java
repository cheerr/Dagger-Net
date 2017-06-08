package org.sangguo.draggertest.modules.home.di;

import dagger.Component;
import org.sangguo.draggertest.internal.di.components.AbstractActivityComponent;
import org.sangguo.draggertest.internal.di.components.ApplicationComponent;
import org.sangguo.draggertest.internal.di.modules.ActivityModule;
import org.sangguo.draggertest.internal.scope.PerActivity;
import org.sangguo.draggertest.modules.common.toast.di.ToastModule2;
import org.sangguo.draggertest.modules.home.activity.MainActivity;

/**
 * Created by chenwei on 2017/6/7.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class,
    MainActivityModule.class, ToastModule2.class})
public interface MainActivityComponent extends AbstractActivityComponent {

  void inject(MainActivity activity);
}
