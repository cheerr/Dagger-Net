package org.sangguo.draggertest.module.abs.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.sangguo.draggertest.TestApplication;
import org.sangguo.draggertest.dagger.di.components.ApplicationComponent;
import org.sangguo.draggertest.dagger.di.modules.ActivityModule;
import org.sangguo.draggertest.module.abs.fragment.BaseFragment;
import org.sangguo.draggertest.module.user.profile.UserProfile;
import org.sangguo.draggertest.ui.dialog.DialogControl;

/**
 * Created by chenwei on 2017/6/7.
 */
public class BaseActivity extends AbstractPresenterLifeCycleActivity implements DialogControl {

  @Inject UserProfile userProfile;

  private List<BroadcastReceiver> mReceiverList;
  private List<Dialog> mDialogList;

  private BaseFragment currFragment;

  //设置当前作用的Fragment
  public void setCurrFragment(BaseFragment currFragment) {
    this.currFragment = currFragment;
  }

  public BaseFragment getCurrFragment() {
    return currFragment;
  }

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

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (currFragment != null && currFragment.onKeyDown(keyCode, event)) {
      return true;
    }

    return super.onKeyDown(keyCode, event);
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

    currFragment = null;
  }

  /************简化布局里View的获取，底层支持ButterKnife，取决于个人习惯*************/

  //简化布局里View的获取
  public <T extends View> T bindView(@IdRes int resId) {
    return (T) findViewById(resId);
  }

  public <T extends View> T bindView(View view, @IdRes int resId) {
    if (view == null) return null;
    return (T) view.findViewById(resId);
  }

  public <T extends View> T bindView(@IdRes int resId, View.OnClickListener listener) {
    T v = (T) findViewById(resId);
    if (v != null) {
      v.setOnClickListener(listener);
    }
    return v;
  }

  public <T extends View> T bindView(View view, @IdRes int resId, View.OnClickListener listener) {
    if (view == null) return null;
    T v = (T) view.findViewById(resId);
    if (v != null) {
      v.setOnClickListener(listener);
    }
    return v;
  }

  public <T extends View> T bindView(@IdRes int resId, boolean visible) {
    T v = (T) findViewById(resId);
    if (v != null) {
      v.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    return v;
  }

  public <T extends View> T bindText(@IdRes int resId, String text) {
    T v = (T) findViewById(resId);
    if (v != null && v instanceof TextView) {
      ((TextView) v).setText(text);
    }
    return v;
  }
}
