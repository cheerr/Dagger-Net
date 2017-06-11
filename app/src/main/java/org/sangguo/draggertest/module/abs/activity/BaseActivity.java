package org.sangguo.draggertest.module.abs.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.sangguo.draggertest.TestApplication;
import org.sangguo.draggertest.module.user.profile.UserProfile;
import org.sangguo.draggertest.dagger.di.components.ApplicationComponent;
import org.sangguo.draggertest.dagger.di.modules.ActivityModule;
import org.sangguo.draggertest.ui.dialog.DialogControl;

/**
 * Created by chenwei on 2017/6/7.
 */
public class BaseActivity extends AbstractPresenterLifeCycleActivity implements DialogControl {

  @Inject UserProfile userProfile;

  private List<BroadcastReceiver> mReceiverList;
  private List<Dialog> mDialogList;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((TestApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  /**
   * 统一管理广播
   */
  protected void registerReceiver(final BroadcastReceiver receiver, final String... filters) {
    final IntentFilter filter = new IntentFilter();
    for (String f : filters) {
      filter.addAction(f);
    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        if (!isFinishing() && receiver != null) {
          receiver.onReceive(context, intent);
        }
      }
    };
    super.registerReceiver(broadcastReceiver, filter);
    if (mReceiverList == null) {
      mReceiverList = new ArrayList<>();
    }
    mReceiverList.add(broadcastReceiver);
  }

  /**
   * @param dialog
   */
  @Override
  public void catchDialog(Dialog dialog) {
    if (mDialogList == null) {
      mDialogList = new ArrayList<>();
    }
    mDialogList.add(dialog);
  }

  @Override
  public void removeDialog(Dialog dialog) {
    if (mDialogList != null) {
      mDialogList.remove(dialog);
    }
  }

  private boolean isAfterOnSaveInstanceState = false;

  @Override
  public void onSaveInstanceState(Bundle bundle) {
    super.onSaveInstanceState(bundle);
    isAfterOnSaveInstanceState = true;
  }

  @Override
  public void onBackPressed() {
    if (interceptBack()) {
      return;
    }
    if (!isAfterOnSaveInstanceState) {
      super.onBackPressed();
    } else {
      finish();
    }
  }

  public boolean interceptBack() {
    if (mDialogList != null && mDialogList.size() > 0) {
      for (int i = mDialogList.size() - 1; i >= 0; i--) {
        Dialog dialog = mDialogList.get(i);
        if (dialog.isShowing()) {
          dialog.dismiss();
          return true;
        }
      }
    }
    return false;
  }

  private boolean mResumed = false;

  @Override
  public void onResume() {
    Log.i("ActivityLife", getClass().getSimpleName() + " onResume()");
    super.onResume();
    mResumed = true;
  }

  @Override
  public void onPause() {
    Log.i("ActivityLife", getClass().getSimpleName() + " onPause()");
    super.onPause();
    mResumed = false;
    if (isFinishing()) {
      clearData();
    }
  }

  @Override
  protected void onDestroy() {
    Log.i("ActivityLife", getClass().getSimpleName() + " onDestroy()");
    super.onDestroy();
    if (mResumed) {
      clearData();
    }
  }

  /**
   * 页面销毁清理必要的数据
   */
  public void clearData() {
    if (mReceiverList != null && mReceiverList.size() > 0) {
      for (BroadcastReceiver br : mReceiverList)
        unregisterReceiver(br);
      mReceiverList.clear();
      mReceiverList = null;
    }

    if (mDialogList != null && mDialogList.size() > 0) {
      mDialogList.clear();
      mDialogList = null;
    }
  }

  //bindView

  public <T> T bindView(@IdRes int id) {
    return (T) findViewById(id);
  }

  public void bindText(@IdRes int id, String string) {
    View view = bindView(id);
    if (view instanceof TextView) {
      ((TextView) view).setText(string);
    }
  }
}
