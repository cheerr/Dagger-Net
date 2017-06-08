package org.sangguo.draggertest;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
import org.sangguo.draggertest.utils.WeakReferenceList;

/**
 * Created by chenwei on 2017/6/7.
 */

public class TestLifeCycleCallbacks implements Application.ActivityLifecycleCallbacks {

  private WeakReferenceList<Activity> activityWeakReferenceList;

  public TestLifeCycleCallbacks() {
    Log.i("TAG", "TestLifeCycleCallbacks_CREATE" + hashCode());
    activityWeakReferenceList = new WeakReferenceList<>();
  }

  @Override
  public void onActivityCreated(Activity activity, Bundle bundle) {
    activityWeakReferenceList.add(activity);
  }

  @Override public void onActivityStarted(Activity activity) {

  }

  @Override public void onActivityResumed(Activity activity) {

  }

  @Override public void onActivityPaused(Activity activity) {

  }

  @Override public void onActivityStopped(Activity activity) {

  }

  @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

  }

  @Override
  public void onActivityDestroyed(Activity activity) {
    activityWeakReferenceList.remove(activity);
  }

  public List<Activity> getActivities() {
    return activityWeakReferenceList.getList();
  }

  public void release() {
    activityWeakReferenceList.clear();
  }

  /**
   * 销毁所有activity
   */
  public void exitApp() {
    List<Activity> activityList = getActivities();
    for (int i = 0; i < activityList.size(); i++)
      activityList.get(i).finish();
    release();
  }
}
