package org.sangguo.draggertest.module.common.toast.di;

import dagger.Module;
import dagger.Provides;
import org.sangguo.draggertest.dagger.scope.PerActivity;
import org.sangguo.draggertest.module.common.toast.ToastType;
import org.sangguo.draggertest.module.common.toast.ToastType1Impl;

/**
 * Created by chenwei on 2017/6/7.
 */

@Module
public class ToastModule1 {

  @Provides @PerActivity ToastType provideStyleToast(ToastType1Impl toastType1) {
    return toastType1;
  }
}
