package org.sangguo.draggertest.internal.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.sangguo.draggertest.TestApplication;
import org.sangguo.draggertest.data.profile.UserProfile;
import org.sangguo.draggertest.data.profile.UserProfileImpl;
import org.sangguo.draggertest.internal.qualifier.ApplicationContext;

@Module
public class ApplicationModule {
  private final TestApplication application;

  public ApplicationModule(TestApplication application) {
    this.application = application;
  }

  //提供全局Context
  @Provides @Singleton @ApplicationContext Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton TestApplication provideApplication() {
    return this.application;
  }

  @Provides @Singleton UserProfile provideUserProfile(UserProfileImpl impl) {
    return impl;
  }
}
