package org.sangguo.draggertest.internal.di.components;

import dagger.Component;
import org.sangguo.draggertest.internal.di.modules.ActivityModule;
import org.sangguo.draggertest.internal.scope.PerActivity;
import org.sangguo.draggertest.modules.abs.BaseActivity;

/**
 * Created by chenwei on 2017/6/7.
 */
@PerActivity// Constraints this component to one-per-application or unscoped bindings.
@Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class
})
public interface AbstractActivityComponent {

  void inject(BaseActivity activity);
}
