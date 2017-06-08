package org.sangguo.draggertest.data.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.sangguo.draggertest.internal.qualifier.ApplicationContext;

/**
 * Created by chenwei on 2017/6/7.
 */
//用户信息的具体实现
@Singleton
public class UserProfileImpl implements UserProfile {

  private Context context;

  private SharedPreferences settings;

  @Inject public UserProfileImpl(@ApplicationContext Context context) {
    this.context = context;
    initSettings("generate");

    Log.i("TAG", "UserProfileImpl_CREATE" + hashCode());
  }

  @Override public void initSettings(String uid) {
    settings = context.getSharedPreferences(context.getPackageName() + "_user_" + uid,
        Context.MODE_PRIVATE);
  }

  @Override public void put(String key, Integer v) {
    settings.edit().putInt(key, v).commit();
  }

  @Override public void put(String key, Long v) {
    settings.edit().putLong(key, v).commit();
  }

  @Override public void put(String key, Boolean v) {
    settings.edit().putBoolean(key, v).commit();
  }

  @Override public void put(String key, String v) {
    settings.edit().putString(key, v).commit();
  }

  @Override public void put(String key, Float v) {
    settings.edit().putFloat(key, v).commit();
  }

  @Override public Integer getInt(String key, Integer df) {
    return settings.getInt(key, df);
  }

  @Override public Long getLong(String key, Long df) {
    return settings.getLong(key, df);
  }

  @Override public Boolean getBoolean(String key, Boolean df) {
    return settings.getBoolean(key, df);
  }

  @Override public Float getFloat(String key, Float df) {
    return settings.getFloat(key, df);
  }

  @Override public String getString(String key) {
    return settings.getString(key, "");
  }
}
