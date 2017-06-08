package org.sangguo.draggertest.internal.di.modules;

import android.app.Activity;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import org.sangguo.draggertest.internal.qualifier.ActivityContext;
import org.sangguo.draggertest.internal.scope.PerActivity;
import org.sangguo.draggertest.modules.abs.BaseActivity;

/**
 * Created by chenwei on 2017/6/7.
 */

@Module
public class ActivityModule {

  private final BaseActivity activity;

  public ActivityModule(BaseActivity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity BaseActivity baseActivity() {
    return this.activity;
  }

  @Provides @PerActivity Activity activity() {
    return this.activity;
  }

  @Provides @PerActivity @ActivityContext Context activityContext() {
    return this.activity;
  }
}
