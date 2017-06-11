package org.sangguo.draggertest.module.common.toast;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import javax.inject.Inject;
import org.sangguo.draggertest.dagger.qualifier.ActivityContext;
import org.sangguo.draggertest.dagger.scope.PerActivity;

/**
 * Created by chenwei on 2017/6/7.
 */

@PerActivity
public class ToastType1Impl implements ToastType {

  private Context context;

  @Inject
  public ToastType1Impl(@ActivityContext Context context) {
    Log.i("TAG", "ToastType1Impl_CREATE" + hashCode());
    this.context = context;
  }

  @Override public void showToast() {
    Toast.makeText(context, "ToastType1Impl", Toast.LENGTH_SHORT).show();
  }
}
