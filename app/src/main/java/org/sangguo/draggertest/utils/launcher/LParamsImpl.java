package org.sangguo.draggertest.utils.launcher;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by chenwei on 2017/6/7.
 */

public class LParamsImpl {

  private Bundle bundle;

  public LParamsImpl() {
    bundle = new Bundle();
  }

  public Bundle build() {
    return bundle;
  }

  public LParamsImpl putAll(Bundle value) {
    bundle.putAll(value);
    return this;
  }

  public LParamsImpl putString(String key, String value) {
    bundle.putString(key, value);
    return this;
  }

  public LParamsImpl putInt(String key, Integer value) {
    bundle.putInt(key, value);
    return this;
  }

  public LParamsImpl putBoolean(String key, Boolean value) {
    bundle.putBoolean(key, value);
    return this;
  }

  public LParamsImpl putFloat(String key, Float value) {
    bundle.putFloat(key, value);
    return this;
  }

  public LParamsImpl putDouble(String key, Double value) {
    bundle.putDouble(key, value);
    return this;
  }

  public LParamsImpl putLong(String key, Long value) {
    bundle.putLong(key, value);
    return this;
  }

  public LParamsImpl putSerializable(String key, Serializable value) {
    bundle.putSerializable(key, value);
    return this;
  }

  public LParamsImpl putParcelable(String key, Parcelable value) {
    bundle.putParcelable(key, value);
    return this;
  }

  public LParamsImpl putParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
    bundle.putParcelableArrayList(key, value);
    return this;
  }
}
