package org.sangguo.draggertest.modules.common.toast.di;

import dagger.Module;
import dagger.Provides;
import org.sangguo.draggertest.internal.scope.PerActivity;
import org.sangguo.draggertest.modules.common.toast.ToastType;
import org.sangguo.draggertest.modules.common.toast.ToastType1Impl;

/**
 * Created by chenwei on 2017/6/7.
 */

@Module
public class ToastModule1 {

  @Provides @PerActivity ToastType provideStyleToast(ToastType1Impl toastType1) {
    return toastType1;
  }
}
