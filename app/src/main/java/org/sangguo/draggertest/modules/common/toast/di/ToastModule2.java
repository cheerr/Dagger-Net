package org.sangguo.draggertest.modules.common.toast.di;

import dagger.Module;
import dagger.Provides;
import org.sangguo.draggertest.internal.scope.PerActivity;
import org.sangguo.draggertest.modules.common.toast.ToastType;
import org.sangguo.draggertest.modules.common.toast.ToastType2Impl;

/**
 * Created by chenwei on 2017/6/7.
 */
@Module
public class ToastModule2 {

  @Provides @PerActivity ToastType provideStyleToast(ToastType2Impl toastType2) {
    return toastType2;
  }
}
