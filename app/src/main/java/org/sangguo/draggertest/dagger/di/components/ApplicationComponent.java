package org.sangguo.draggertest.dagger.di.components;

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import org.sangguo.draggertest.TestApplication;
import org.sangguo.draggertest.dagger.di.modules.ApplicationModule;
import org.sangguo.draggertest.dagger.qualifier.ApplicationContext;
import org.sangguo.draggertest.module.user.profile.UserProfile;

/**
 * Created by chenwei on 2017/6/7.
 */

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  //Exposed to sub-graphs.
  @ApplicationContext Context applicationContext();

  UserProfile userProfile();

  TestApplication application();
}
