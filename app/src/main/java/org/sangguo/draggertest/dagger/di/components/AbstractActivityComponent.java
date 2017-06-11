package org.sangguo.draggertest.dagger.di.components;

import dagger.Component;
import org.sangguo.draggertest.dagger.di.modules.ActivityModule;
import org.sangguo.draggertest.dagger.scope.PerActivity;

/**
 * Created by chenwei on 2017/6/7.
 */
@PerActivity// Constraints this component to one-per-application or unscoped bindings.
@Component(dependencies = ApplicationComponent.class, modules = {
    ActivityModule.class
})
public interface AbstractActivityComponent {
}
